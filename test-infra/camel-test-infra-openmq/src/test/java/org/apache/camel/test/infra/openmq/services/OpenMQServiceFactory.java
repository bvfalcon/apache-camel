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
package org.apache.camel.test.infra.openmq.services;

import org.apache.camel.test.infra.common.services.SimpleTestServiceBuilder;
import org.apache.camel.test.infra.common.services.SingletonService;
import org.junit.jupiter.api.extension.ExtensionContext;

public final class OpenMQServiceFactory {

    public static class SingletonOpenMQService extends SingletonService<OpenMQService> implements OpenMQService {
        public SingletonOpenMQService(OpenMQService service, String name) {
            super(service, name);
        }

        @Override
        public String serviceAddress() {
            return getService().serviceAddress();
        }

        @Override
        public String userName() {
            return getService().userName();
        }

        @Override
        public String password() {
            return getService().password();
        }

        @Override
        public void beforeAll(ExtensionContext extensionContext) {
            addToStore(extensionContext);
        }

        @Override
        public void afterAll(ExtensionContext extensionContext) {
            // NO-OP
        }

        @Override
        public void afterEach(ExtensionContext extensionContext) {
            // NO-OP
        }

        @Override
        public void beforeEach(ExtensionContext extensionContext) {
            addToStore(extensionContext);
        }

        @Override
        public void restart() {
            getService().restart();
        }

        @Override
        public OpenMQService getService() {
            return super.getService();
        }
    }

    private static SimpleTestServiceBuilder<OpenMQService> nonPersistentInstanceBuilder;
    private static OpenMQService nonPersistentService;

    private static SimpleTestServiceBuilder<OpenMQService> persistentInstanceBuilder;
    private static OpenMQService persistentService;

    private OpenMQServiceFactory() {

    }

    public static SimpleTestServiceBuilder<OpenMQService> builder() {
        return new SimpleTestServiceBuilder<>("activemq");
    }

    public static OpenMQService createService() {
        return builder()
                .addLocalMapping(OpenMQEmbeddedService::new)
                .addRemoteMapping(OpenMQRemoteService::new)
                .build();
    }

    /**
     * Creates a new instance of an embedded OpenMQ
     * 
     * @return a new instance of an embedded OpenMQ
     */
    public static synchronized OpenMQService createVMService() {
        return createSingletonVMService();
    }

    /**
     * Creates a new instance of an embedded OpenMQ. It may use a single instance if possible/supported.
     * 
     * @return a new instance of an embedded OpenMQ
     */
    public static synchronized OpenMQService createVMServiceInstance() {
        SimpleTestServiceBuilder<OpenMQService> instance = new SimpleTestServiceBuilder<>("activemq");
        instance.addLocalMapping(OpenMQVMService::new);

        return instance.build();
    }

    /**
     * Creates or reuses a new singleton instance of an embedded OpenMQ
     * 
     * @return an instance of an embedded OpenMQ
     */
    public static synchronized OpenMQService createSingletonVMService() {
        if (nonPersistentService == null) {
            if (nonPersistentInstanceBuilder == null) {
                nonPersistentInstanceBuilder = new SimpleTestServiceBuilder<>("activemq");

                nonPersistentInstanceBuilder
                        .addLocalMapping(() -> new SingletonOpenMQService(new OpenMQVMService(), "activemq"));
            }

            nonPersistentService = nonPersistentInstanceBuilder.build();
        }

        return nonPersistentService;
    }

    /**
     * Creates a new instance of a persistent embedded OpenMQ. It may use a single instance if possible/supported.
     * 
     * @return a new instance of a persistent embedded OpenMQ
     */
    public static synchronized OpenMQService createPersistentVMService() {
        return createSingletonPersistentVMService();
    }

    /**
     * Creates a new instance of a persistent embedded OpenMQ
     * 
     * @return a new instance of a persistent embedded OpenMQ
     */
    public static synchronized OpenMQService createPersistentVMServiceInstance() {
        SimpleTestServiceBuilder<OpenMQService> instance = new SimpleTestServiceBuilder<>("activemq");

        instance.addLocalMapping(OpenMQPersistentVMService::new);

        return instance.build();
    }

    /**
     * Creates or reuses a new singleton instance of a persistent embedded OpenMQ
     * 
     * @return an instance of a persistent embedded OpenMQ
     */
    public static synchronized OpenMQService createSingletonPersistentVMService() {
        if (persistentService == null) {
            if (persistentInstanceBuilder == null) {
                persistentInstanceBuilder = new SimpleTestServiceBuilder<>("activemq");

                persistentInstanceBuilder
                        .addLocalMapping(
                                () -> new SingletonOpenMQService(new OpenMQPersistentVMService(), "activemq-persistent"));
            }

            persistentService = persistentInstanceBuilder.build();
        }

        return persistentService;
    }
}
