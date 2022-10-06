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
package org.apache.camel.component.stomp;

import javax.net.ssl.SSLContext;

import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.junit.EmbeddedActiveMQExtension;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.Registry;
import org.apache.camel.support.SimpleRegistry;
import org.apache.camel.support.jsse.KeyManagersParameters;
import org.apache.camel.support.jsse.KeyStoreParameters;
import org.apache.camel.support.jsse.SSLContextParameters;
import org.apache.camel.support.jsse.TrustManagersParameters;
import org.apache.camel.test.AvailablePortFinder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.fusesource.stomp.client.Stomp;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class StompBaseTest extends CamelTestSupport {

    static int port = AvailablePortFinder.getNextAvailable();
    static String protocol = "CORE";
    private static Configuration config;
    static {
        try {
            config = new ConfigurationImpl()
                    .addAcceptorConfiguration(protocol, "stomp://localhost:" + port + "?protocols=STOMP")
                    .setSecurityEnabled(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @RegisterExtension
    public static EmbeddedActiveMQExtension service = new EmbeddedActiveMQExtension(config);

    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected int numberOfMessages = 100;
    private SSLContextParameters serverSslContextParameters;
    private SSLContext serverSslContext;
    private SSLContextParameters clientSslContextParameters;
    private SSLContext clientSslContext;

    protected boolean isUseSsl() {
        return false;
    }

    @Override
    public boolean isUseRouteBuilder() {
        return false;
    }

    @Override
    protected Registry createCamelRegistry() {
        SimpleRegistry registry = new SimpleRegistry();
        if (isUseSsl()) {
            registry.bind("sslContextParameters", getClientSSLContextParameters());
        }

        return registry;
    }

    protected Stomp createStompClient() throws Exception {
        Stomp stomp;

        if (isUseSsl()) {
            stomp = new Stomp("ssl://localhost:" + port);
            stomp.setSslContext(getClientSSLContext());
        } else {
            stomp = new Stomp("tcp://localhost:" + port);
        }

        return stomp;
    }

    protected SSLContextParameters getServerSSLContextParameters() {
        if (serverSslContextParameters == null) {
            serverSslContextParameters = getSSLContextParameters("jsse/server.keystore", "password");
        }

        return serverSslContextParameters;
    }

    protected SSLContext getServerSSLContext() throws Exception {
        if (serverSslContext == null) {
            serverSslContext = getServerSSLContextParameters().createSSLContext(context);
        }

        return serverSslContext;
    }

    protected SSLContextParameters getClientSSLContextParameters() {
        if (clientSslContextParameters == null) {
            clientSslContextParameters = getSSLContextParameters("jsse/client.keystore", "password");
        }

        return clientSslContextParameters;
    }

    protected SSLContext getClientSSLContext() throws Exception {
        if (clientSslContext == null) {
            clientSslContext = getClientSSLContextParameters().createSSLContext(context);
        }

        return clientSslContext;
    }

    private SSLContextParameters getSSLContextParameters(String path, String password) {
        // need an early camel context dummy due to ActiveMQEmbeddedService is eager initialized
        CamelContext dummy = new DefaultCamelContext();

        KeyStoreParameters ksp = new KeyStoreParameters();
        ksp.setCamelContext(dummy);
        ksp.setResource(path);
        ksp.setPassword(password);

        KeyManagersParameters kmp = new KeyManagersParameters();
        kmp.setCamelContext(dummy);
        kmp.setKeyPassword(password);
        kmp.setKeyStore(ksp);

        TrustManagersParameters tmp = new TrustManagersParameters();
        tmp.setCamelContext(dummy);
        tmp.setKeyStore(ksp);

        SSLContextParameters sslContextParameters = new SSLContextParameters();
        sslContextParameters.setCamelContext(dummy);
        sslContextParameters.setKeyManagers(kmp);
        sslContextParameters.setTrustManagers(tmp);

        return sslContextParameters;
    }
}
