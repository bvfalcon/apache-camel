/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.jms.issues;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.artemis.api.core.ActiveMQException;
import org.apache.activemq.artemis.api.core.ActiveMQExceptionType;
import org.apache.activemq.artemis.api.core.Message;
import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ServerSession;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.apache.activemq.artemis.core.server.plugin.ActiveMQServerMessagePlugin;
import org.apache.activemq.artemis.core.transaction.Transaction;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.Synchronization;
import org.apache.camel.test.infra.activemq.services.AbstractActiveMQEmbeddedService;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentAutoAcknowledge;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests CAMEL-5769. Camel JMS producer can block a thread under specific circumstances.
 */
@Timeout(30)
public class JmsBlockedAsyncRoutingEngineTest extends CamelTestSupport {

    private static final Logger LOG = LoggerFactory.getLogger(JmsBlockedAsyncRoutingEngineTest.class);
    private EmbeddedActiveMQ embeddedServer;
    private final CountDownLatch latch = new CountDownLatch(5);
    private final Synchronization callback = new Synchronization() {
        @Override
        public void onFailure(Exchange exchange) {
            LOG.info(">>>> Callback onFailure");
            latch.countDown();
        }

        @Override
        public void onComplete(Exchange exchange) {
            LOG.info(">>>> Callback onComplete");
            latch.countDown();
        }
    };

    public void startBroker() throws Exception {
        String brokerName = "JmsBlockedAsyncRoutingEngineTest-broker-" + System.currentTimeMillis();
        String brokerUri = "vm://" + brokerName;

        Configuration config = new ConfigurationImpl();
        config.setName(brokerName);
        config.addConnectorConfiguration("in-vm", brokerUri);
        config.setPersistenceEnabled(false);
        // This Broker Plugin simulates Producer Flow Control by delaying the broker's ACK by 2 seconds
        config.registerBrokerPlugin(new DelayerBrokerPlugin());

        embeddedServer = new EmbeddedActiveMQ();
        embeddedServer.setConfiguration(config);
        embeddedServer.start();
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();
        startBroker();
        ConnectionFactory connectionFactory
                = new ActiveMQConnectionFactory(AbstractActiveMQEmbeddedService.getBrokerUri(embeddedServer));
        camelContext.addComponent("activemq", jmsComponentAutoAcknowledge(connectionFactory));
        return camelContext;
    }

    @Test
    public void testBlockedAsyncRoutingEngineTest() throws Exception {
        // 0. This message takes 2000ms to ACK from the broker due to the DelayerBrokerPlugin
        // Until then, the correlation ID doesn't get updated locally
        try {
            template.asyncRequestBody(
                    "activemq:queue:JmsBlockedAsyncRoutingEngineTest?requestTimeout=500&useMessageIDAsCorrelationID=true",
                    "hello");
        } catch (Exception e) {
        }

        // 1. We wait a bit for the CorrelationTimeoutMap purge process to run
        Thread.sleep(3000);

        // 2. We send 5 messages that take 2 seconds so that they time out
        template.asyncCallbackRequestBody(
                "activemq:queue:JmsBlockedAsyncRoutingEngineTest?requestTimeout=500&useMessageIDAsCorrelationID=true", "beSlow",
                callback);
        template.asyncCallbackRequestBody(
                "activemq:queue:JmsBlockedAsyncRoutingEngineTest?requestTimeout=500&useMessageIDAsCorrelationID=true", "beSlow",
                callback);
        template.asyncCallbackRequestBody(
                "activemq:queue:JmsBlockedAsyncRoutingEngineTest?requestTimeout=500&useMessageIDAsCorrelationID=true", "beSlow",
                callback);
        template.asyncCallbackRequestBody(
                "activemq:queue:JmsBlockedAsyncRoutingEngineTest?requestTimeout=500&useMessageIDAsCorrelationID=true", "beSlow",
                callback);
        template.asyncCallbackRequestBody(
                "activemq:queue:JmsBlockedAsyncRoutingEngineTest?requestTimeout=500&useMessageIDAsCorrelationID=true", "beSlow",
                callback);

        // 3. We assert that we were notified of all timeout exceptions
        assertTrue(latch.await(3000, TimeUnit.MILLISECONDS));
    }

    @AfterEach
    public void cleanup() {
        LOG.info(">>>>> Latch countdown count was: {}", latch.getCount());
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                from("activemq:queue:JmsBlockedAsyncRoutingEngineTest?concurrentConsumers=5&useMessageIDAsCorrelationID=true&transacted=true")
                        .filter().simple("${in.body} == 'beSlow'")
                        .delay(constant(2000))
                        .log(">>>>> Received message on test queue")
                        .setBody(constant("Reply"))
                        .log(">>>>> Sending back reply");

            }
        };
    }

    private static class DelayerBrokerPlugin implements ActiveMQServerMessagePlugin {
        int i;

        @Override
        public void beforeSend(
                ServerSession session, Transaction tx, Message messageSend, boolean direct, boolean noAutoCreateQueue)
                throws ActiveMQException {
            //public void send(ProducerBrokerExchange producerExchange, Message messageSend) throws Exception {
            String destinationName = messageSend.getAddress();
            LOG.info("******** Received message for destination {}", destinationName);

            // do not intercept sends to DLQ
            if (destinationName.toLowerCase().contains("JmsBlockedAsyncRoutingEngineTest") && i == 0) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new ActiveMQException(e.getMessage(), e, ActiveMQExceptionType.INTERNAL_ERROR);
                }
                LOG.info("******** Waited 2 seconds for destination: {}", destinationName);
                i++;
            }
        }

    }

}
