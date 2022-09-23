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

package org.apache.camel.test.infra.openmq.common;

import jakarta.jms.ConnectionFactory;

import org.apache.camel.test.infra.openmq.services.AbstractOpenMQEmbeddedService;
import org.apache.camel.test.infra.openmq.services.ConnectionFactoryAware;
import org.apache.camel.test.infra.openmq.services.OpenMQService;
import org.apache.camel.test.infra.openmq.services.OpenMQServiceFactory;

import com.sun.messaging.BasicConnectionFactory;
import com.sun.messaging.ConnectionConfiguration;

public final class ConnectionFactoryHelper {
    private ConnectionFactoryHelper() {
    }

    public static ConnectionFactory createConnectionFactory(OpenMQService service) {
        return createConnectionFactory(service, null);
    }

    public static ConnectionFactory createConnectionFactory(OpenMQService service, Integer maximumRedeliveries) {
        if (service instanceof ConnectionFactoryAware) {
            return createConnectionFactory(((AbstractOpenMQEmbeddedService) service).getVmURL(), maximumRedeliveries);
        }

        if (service instanceof OpenMQServiceFactory.SingletonOpenMQService) {
            return createConnectionFactory(((OpenMQServiceFactory.SingletonOpenMQService) service).getService(),
                    maximumRedeliveries);
        }

        throw new UnsupportedOperationException("The test service does not implement ConnectionFactoryAware");
    }

    public static ConnectionFactory createConnectionFactory(String url, Integer maximumRedeliveries) {
        BasicConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();
        return createConnectionFactory(connectionFactory, maximumRedeliveries);
    }

    public static ConnectionFactory createConnectionFactory(
            BasicConnectionFactory connectionFactory, Integer maximumRedeliveries) {
        // When using asyncSend, producers will not be guaranteed to send in the order we
        // have in the tests (which may be confusing for queues) so we need this set to false.
        // Another way of guaranteeing order is to use persistent messages or transactions.
        /*connectionFactory.setUseAsyncSend(false);
        connectionFactory.setAlwaysSessionAsync(false);
        if (maximumRedeliveries != null) {
            connectionFactory.getRedeliveryPolicy().setMaximumRedeliveries(maximumRedeliveries);
        }
        connectionFactory.setTrustAllPackages(true);
        connectionFactory.setWatchTopicAdvisories(false);*/
        return connectionFactory;
    }

    public static ConnectionFactory createPersistentConnectionFactory(String url) {
        BasicConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();
        return createPersistentConnectionFactory(connectionFactory);

    }

    public static ConnectionFactory createPersistentConnectionFactory(BasicConnectionFactory connectionFactory) {
        connectionFactory.setProperty(ConnectionConfiguration.imqJMSDeliveryMode, ConnectionConfiguration.JMSDeliveryMode_PERSISTENT);
        /*connectionFactory.setCopyMessageOnSend(false);
        connectionFactory.setOptimizeAcknowledge(true);
        connectionFactory.setOptimizedMessageDispatch(true);
        connectionFactory.setAlwaysSessionAsync(false);
        connectionFactory.setTrustAllPackages(true);*/
        return connectionFactory;
    }
}
