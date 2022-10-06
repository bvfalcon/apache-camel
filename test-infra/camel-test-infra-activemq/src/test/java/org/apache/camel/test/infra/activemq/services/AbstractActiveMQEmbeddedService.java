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

package org.apache.camel.test.infra.activemq.services;

import java.util.Map;

import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractActiveMQEmbeddedService implements ActiveMQService {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractActiveMQEmbeddedService.class);
    private final EmbeddedActiveMQ embeddedServer;

    public AbstractActiveMQEmbeddedService() {
        this(ActiveMQEmbeddedServiceBuilder.defaultBroker().embeddedServer());
    }

    public AbstractActiveMQEmbeddedService(EmbeddedActiveMQ embeddedServer) {
        this.embeddedServer = embeddedServer;
    }

    @Override
    public void initialize() {
        LOG.info("Trying to start the embedded ActiveMQ");
        try {
            embeddedServer.start();
            LOG.info("Embedded ActiveMQ running at {}", serviceAddress());
        } catch (Exception e) {
            LOG.warn("Unable to start embedded ActiveMQ broker: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    @Override
    public void shutdown() {
        LOG.debug("Trying to stop the embedded ActiveMQ");
        try {
            embeddedServer.stop();
            LOG.debug("Embedded ActiveMQ stopped");
        } catch (Exception e) {
            LOG.warn("Error stopping embedded ActiveMQ broker: {}", e.getMessage(), e);
        }
    }

    public void restart() {
        shutdown();

        LOG.info("Trying to start the restart ActiveMQ");
        try {
            embeddedServer.stop();
            embeddedServer.start();
            LOG.info("Embedded ActiveMQ running at {}", serviceAddress());
        } catch (Exception e) {
            LOG.warn("Unable to start embedded ActiveMQ broker: {}", e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    @Override
    public String userName() {
        return null;
    }

    @Override
    public String password() {
        return null;
    }

    public int getConnectionCount() {
        return embeddedServer.getActiveMQServer().getConnectionCount();
    }

    public EmbeddedActiveMQ getEmbeddedServer() {
        return embeddedServer;
    }

    protected String getBrokerUri() {
        return getBrokerUri(embeddedServer);
    }

    public static String getBrokerUri(EmbeddedActiveMQ server) {
        try {
            Map<String, Object> map = server.getActiveMQServer().getConfiguration().getAcceptorConfigurations().iterator()
                    .next().getCombinedParams();
            if (map.get("host") != null) {
                return "tcp://" + map.get("host") + ":" + map.get("port");
            } else {
                return "vm://" + map.values().iterator().next();
            }
        } catch (Exception e) {
            LOG.warn("Unable to get ActiveMQ broker address: {}", e.getMessage(), e);
            return null;
        }
    }

    public abstract String getVmURL();

    public abstract String getVmURL(boolean create);

    protected String getVmURL(boolean failoverURL, boolean create) {
        return failoverURL
                ? String.format("failover:(%s?create=%b)", getBrokerUri(), create)
                : getBrokerUri() + "?create=" + create;
    }
}
