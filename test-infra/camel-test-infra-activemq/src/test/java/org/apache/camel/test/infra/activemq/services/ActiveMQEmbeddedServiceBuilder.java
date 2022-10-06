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

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * This is a builder class for the embedded ActiveMQ BrokerService. Since it is tightly integrated into the tests of
 * some components, we need to have flexibility setting it up. Therefore, in most cases for tests that rely on purely
 * embedded ActiveMQ, they can use this to wrap the broker service into the test-infra compatible service that can be
 * managed by Junit 5.
 */
public final class ActiveMQEmbeddedServiceBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(ActiveMQEmbeddedServiceBuilder.class);

    private static final LongAdder BROKER_COUNT = new LongAdder();
    private final EmbeddedActiveMQ embeddedServer;

    private ActiveMQEmbeddedServiceBuilder() {
        embeddedServer = new EmbeddedActiveMQ();
    }

    public ActiveMQEmbeddedServiceBuilder withBrokerName(Class<?> testClass) {
        return withBrokerName(testClass.getSimpleName());
    }

    public ActiveMQEmbeddedServiceBuilder withBrokerName(String brokerName) {
        getOrInitConfiguration().setName(brokerName);

        return this;
    }

    public ActiveMQEmbeddedServiceBuilder withBrokerName(Class<?> testClass, String name) {
        return withBrokerName(testClass.getSimpleName(), name);
    }

    public ActiveMQEmbeddedServiceBuilder withBrokerName(String brokerName, String name) {
        withBrokerName(brokerName + (name != null ? "-" + name : ""));

        return this;
    }

    public ActiveMQEmbeddedServiceBuilder withDataDirectory(Path dataDirectory) {
        return withDataDirectory(dataDirectory.toAbsolutePath().toString());
    }

    public ActiveMQEmbeddedServiceBuilder withDataDirectory(String dataDirectory) {
        //brokerService.setDataDirectory(dataDirectory);

        return this;
    }

    public ActiveMQEmbeddedServiceBuilder withPersistent(boolean persistent) {
        getOrInitConfiguration().setPersistenceEnabled(persistent);
        return this;
    }

    private Configuration getOrInitConfiguration() {
        if (embeddedServer.getConfiguration() == null) {
            Configuration configuration = new ConfigurationImpl();
            embeddedServer.setConfiguration(configuration);
        }
        return embeddedServer.getConfiguration();
    }

    public ActiveMQEmbeddedServiceBuilder withUseJmx(boolean useJmx) {
        getOrInitConfiguration().setJMXManagementEnabled(useJmx);
        return this;
    }

    public ActiveMQEmbeddedServiceBuilder withAdvisorySupport(boolean advisorySupport) {
        //brokerService.setAdvisorySupport(advisorySupport);
        return this;
    }

    public ActiveMQEmbeddedServiceBuilder withDeleteAllMessagesOnStartup(boolean deletePersistentMessagesOnStartup) {
        //brokerService.setDeleteAllMessagesOnStartup(deletePersistentMessagesOnStartup);
        return this;
    }

    public ActiveMQEmbeddedServiceBuilder withTimeBeforePurgeTempDestinations(int timeBeforePurgeTempDestinations) {
        //brokerService.setTimeBeforePurgeTempDestinations(timeBeforePurgeTempDestinations);
        return this;
    }

    /*public ActiveMQEmbeddedServiceBuilder withCustomSetup(Consumer<BrokerService> consumer) {
        consumer.accept(brokerService);
    
        return this;
    }*/

    public ActiveMQEmbeddedServiceBuilder withNettyTransport() {
        return withNettyTransport(0);
    }

    public ActiveMQEmbeddedServiceBuilder withSecurityEnabled(boolean enabled) {
        getOrInitConfiguration().setSecurityEnabled(enabled);
        return this;
    }

    public ActiveMQEmbeddedServiceBuilder withNettyTransport(int port) {
        return withTransport("netty", "tcp://0.0.0.0:" + port);
    }

    public ActiveMQEmbeddedServiceBuilder withAmqpTransport(int port) {
        return withTransport("amqp", "tcp://0.0.0.0:" + port);
    }

    public ActiveMQEmbeddedServiceBuilder withMqttTransport(int port) {
        return withTransport("mqtt", "tcp://0.0.0.0:" + port);
    }

    public ActiveMQEmbeddedServiceBuilder withStompTransport(int port) {
        return withTransport("stomp", "tcp://localhost:" + port);
    }

    public ActiveMQEmbeddedServiceBuilder withVmTransport() {
        return withTransport("in-vm", "vm://0");
    }

    public ActiveMQEmbeddedServiceBuilder withTransport(String name, String uri) {
        try {
            getOrInitConfiguration().addAcceptorConfiguration(name, uri);
        } catch (Exception e) {
            fail("Unable to add new transport: " + e.getMessage());
        }

        return this;
    }

    EmbeddedActiveMQ embeddedServer() {
        return embeddedServer;
    }

    public ActiveMQEmbeddedService build() {
        return new ActiveMQEmbeddedService(embeddedServer);
    }

    @Deprecated
    public ActiveMQEmbeddedService buildWithRecycle() {
        return build();
    }

    public static ActiveMQEmbeddedServiceBuilder bare() {
        return new ActiveMQEmbeddedServiceBuilder();
    }

    private static String generateDataDirectoryPathForInstance(String name) {
        final String dataDirectoryPath = ActiveMQEmbeddedServiceBuilder.class.getResource("/").getFile() + name;

        final File dataDirectory = new File(dataDirectoryPath);
        if (dataDirectory.exists()) {
            try {
                FileUtils.deleteDirectory(dataDirectory);
            } catch (IOException e) {
                LOG.warn(
                        "Could not delete the data directory at {}: {} (the error will be ignored, but the tests are likely to fail)",
                        dataDirectoryPath, e.getMessage());
            }
        }

        return dataDirectoryPath;
    }

    private static String generateSemiUniqueBrokerName() {
        final String semiUniqueName
                = ActiveMQEmbeddedServiceBuilder.class.getSimpleName() + "-" + BROKER_COUNT.longValue() + "."
                  + ThreadLocalRandom.current().nextInt(1000);

        BROKER_COUNT.increment();
        return semiUniqueName;
    }

    public static ActiveMQEmbeddedServiceBuilder defaultBroker() {
        final String semiUniqueName = generateSemiUniqueBrokerName();

        return defaultBroker(semiUniqueName);
    }

    public static ActiveMQEmbeddedServiceBuilder defaultBroker(String name) {
        final String dataDirectoryPath = generateDataDirectoryPathForInstance(name);

        return new ActiveMQEmbeddedServiceBuilder()
                .withDeleteAllMessagesOnStartup(true)
                .withBrokerName(name)
                .withAdvisorySupport(false)
                .withUseJmx(false)
                .withDataDirectory(dataDirectoryPath)
                .withSecurityEnabled(false);
    }

    public static ActiveMQEmbeddedServiceBuilder persistentBroker() {
        String semiUniqueName = "persistent" + generateSemiUniqueBrokerName();

        BROKER_COUNT.increment();

        return persistentBroker(semiUniqueName);
    }

    public static ActiveMQEmbeddedServiceBuilder persistentBroker(String name) {
        final String dataDirectoryPath = generateDataDirectoryPathForInstance(name);

        return new ActiveMQEmbeddedServiceBuilder()
                .withDeleteAllMessagesOnStartup(true)
                .withBrokerName(name)
                .withAdvisorySupport(false)
                .withUseJmx(false)
                .withPersistent(true)
                .withDataDirectory(dataDirectoryPath);
    }

}
