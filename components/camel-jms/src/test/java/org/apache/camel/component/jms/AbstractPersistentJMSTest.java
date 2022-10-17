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

package org.apache.camel.component.jms;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.junit.EmbeddedActiveMQExtension;
import org.apache.activemq.artemis.tests.util.CFUtil;
import org.apache.camel.CamelContext;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentAutoAcknowledge;

@Tags({ @Tag("jms") })
public abstract class AbstractPersistentJMSTest extends CamelTestSupport {

    static String protocol = "CORE";
    private static Configuration config;
    static {
        try {
            config = new ConfigurationImpl().addAcceptorConfiguration(protocol, "vm://0").setSecurityEnabled(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @RegisterExtension
    public static EmbeddedActiveMQExtension service = new EmbeddedActiveMQExtension(config);

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();

        createConnectionFactory(camelContext);

        return camelContext;
    }

    protected void createConnectionFactory(CamelContext camelContext) {
        ConnectionFactory connectionFactory = CFUtil.createConnectionFactory(protocol, service.getVmURL());
        camelContext.addComponent("activemq", jmsComponentAutoAcknowledge(connectionFactory));
    }
}
