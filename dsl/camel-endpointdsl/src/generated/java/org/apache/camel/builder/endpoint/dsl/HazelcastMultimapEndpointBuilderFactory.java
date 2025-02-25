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
package org.apache.camel.builder.endpoint.dsl;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;
import jakarta.annotation.Generated;
import org.apache.camel.builder.EndpointConsumerBuilder;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.apache.camel.builder.endpoint.AbstractEndpointBuilder;

/**
 * Perform operations on Hazelcast distributed multimap.
 * 
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.EndpointDslMojo")
public interface HazelcastMultimapEndpointBuilderFactory {


    /**
     * Builder for endpoint consumers for the Hazelcast Multimap component.
     */
    public interface HazelcastMultimapEndpointConsumerBuilder
            extends
                EndpointConsumerBuilder {
        default AdvancedHazelcastMultimapEndpointConsumerBuilder advanced() {
            return (AdvancedHazelcastMultimapEndpointConsumerBuilder) this;
        }
        /**
         * To specify a default operation to use, if no operation header has
         * been provided.
         * 
         * The option is a:
         * &lt;code&gt;org.apache.camel.component.hazelcast.HazelcastOperation&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param defaultOperation the value to set
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointConsumerBuilder defaultOperation(
                org.apache.camel.component.hazelcast.HazelcastOperation defaultOperation) {
            doSetProperty("defaultOperation", defaultOperation);
            return this;
        }
        /**
         * To specify a default operation to use, if no operation header has
         * been provided.
         * 
         * The option will be converted to a
         * &lt;code&gt;org.apache.camel.component.hazelcast.HazelcastOperation&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param defaultOperation the value to set
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointConsumerBuilder defaultOperation(
                String defaultOperation) {
            doSetProperty("defaultOperation", defaultOperation);
            return this;
        }
        /**
         * The hazelcast instance reference which can be used for hazelcast
         * endpoint.
         * 
         * The option is a:
         * &lt;code&gt;com.hazelcast.core.HazelcastInstance&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param hazelcastInstance the value to set
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointConsumerBuilder hazelcastInstance(
                com.hazelcast.core.HazelcastInstance hazelcastInstance) {
            doSetProperty("hazelcastInstance", hazelcastInstance);
            return this;
        }
        /**
         * The hazelcast instance reference which can be used for hazelcast
         * endpoint.
         * 
         * The option will be converted to a
         * &lt;code&gt;com.hazelcast.core.HazelcastInstance&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param hazelcastInstance the value to set
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointConsumerBuilder hazelcastInstance(
                String hazelcastInstance) {
            doSetProperty("hazelcastInstance", hazelcastInstance);
            return this;
        }
        /**
         * The hazelcast instance reference name which can be used for hazelcast
         * endpoint. If you don't specify the instance reference, camel use the
         * default hazelcast instance from the camel-hazelcast instance.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param hazelcastInstanceName the value to set
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointConsumerBuilder hazelcastInstanceName(
                String hazelcastInstanceName) {
            doSetProperty("hazelcastInstanceName", hazelcastInstanceName);
            return this;
        }
    }

    /**
     * Advanced builder for endpoint consumers for the Hazelcast Multimap
     * component.
     */
    public interface AdvancedHazelcastMultimapEndpointConsumerBuilder
            extends
                EndpointConsumerBuilder {
        default HazelcastMultimapEndpointConsumerBuilder basic() {
            return (HazelcastMultimapEndpointConsumerBuilder) this;
        }
        /**
         * Allows for bridging the consumer to the Camel routing Error Handler,
         * which mean any exceptions occurred while the consumer is trying to
         * pickup incoming messages, or the likes, will now be processed as a
         * message and handled by the routing Error Handler. By default the
         * consumer will use the org.apache.camel.spi.ExceptionHandler to deal
         * with exceptions, that will be logged at WARN or ERROR level and
         * ignored.
         * 
         * The option is a: &lt;code&gt;boolean&lt;/code&gt; type.
         * 
         * Default: false
         * Group: consumer (advanced)
         * 
         * @param bridgeErrorHandler the value to set
         * @return the dsl builder
         */
        default AdvancedHazelcastMultimapEndpointConsumerBuilder bridgeErrorHandler(
                boolean bridgeErrorHandler) {
            doSetProperty("bridgeErrorHandler", bridgeErrorHandler);
            return this;
        }
        /**
         * Allows for bridging the consumer to the Camel routing Error Handler,
         * which mean any exceptions occurred while the consumer is trying to
         * pickup incoming messages, or the likes, will now be processed as a
         * message and handled by the routing Error Handler. By default the
         * consumer will use the org.apache.camel.spi.ExceptionHandler to deal
         * with exceptions, that will be logged at WARN or ERROR level and
         * ignored.
         * 
         * The option will be converted to a &lt;code&gt;boolean&lt;/code&gt;
         * type.
         * 
         * Default: false
         * Group: consumer (advanced)
         * 
         * @param bridgeErrorHandler the value to set
         * @return the dsl builder
         */
        default AdvancedHazelcastMultimapEndpointConsumerBuilder bridgeErrorHandler(
                String bridgeErrorHandler) {
            doSetProperty("bridgeErrorHandler", bridgeErrorHandler);
            return this;
        }
        /**
         * To let the consumer use a custom ExceptionHandler. Notice if the
         * option bridgeErrorHandler is enabled then this option is not in use.
         * By default the consumer will deal with exceptions, that will be
         * logged at WARN or ERROR level and ignored.
         * 
         * The option is a:
         * &lt;code&gt;org.apache.camel.spi.ExceptionHandler&lt;/code&gt; type.
         * 
         * Group: consumer (advanced)
         * 
         * @param exceptionHandler the value to set
         * @return the dsl builder
         */
        default AdvancedHazelcastMultimapEndpointConsumerBuilder exceptionHandler(
                org.apache.camel.spi.ExceptionHandler exceptionHandler) {
            doSetProperty("exceptionHandler", exceptionHandler);
            return this;
        }
        /**
         * To let the consumer use a custom ExceptionHandler. Notice if the
         * option bridgeErrorHandler is enabled then this option is not in use.
         * By default the consumer will deal with exceptions, that will be
         * logged at WARN or ERROR level and ignored.
         * 
         * The option will be converted to a
         * &lt;code&gt;org.apache.camel.spi.ExceptionHandler&lt;/code&gt; type.
         * 
         * Group: consumer (advanced)
         * 
         * @param exceptionHandler the value to set
         * @return the dsl builder
         */
        default AdvancedHazelcastMultimapEndpointConsumerBuilder exceptionHandler(
                String exceptionHandler) {
            doSetProperty("exceptionHandler", exceptionHandler);
            return this;
        }
        /**
         * Sets the exchange pattern when the consumer creates an exchange.
         * 
         * The option is a:
         * &lt;code&gt;org.apache.camel.ExchangePattern&lt;/code&gt; type.
         * 
         * Group: consumer (advanced)
         * 
         * @param exchangePattern the value to set
         * @return the dsl builder
         */
        default AdvancedHazelcastMultimapEndpointConsumerBuilder exchangePattern(
                org.apache.camel.ExchangePattern exchangePattern) {
            doSetProperty("exchangePattern", exchangePattern);
            return this;
        }
        /**
         * Sets the exchange pattern when the consumer creates an exchange.
         * 
         * The option will be converted to a
         * &lt;code&gt;org.apache.camel.ExchangePattern&lt;/code&gt; type.
         * 
         * Group: consumer (advanced)
         * 
         * @param exchangePattern the value to set
         * @return the dsl builder
         */
        default AdvancedHazelcastMultimapEndpointConsumerBuilder exchangePattern(
                String exchangePattern) {
            doSetProperty("exchangePattern", exchangePattern);
            return this;
        }
    }

    /**
     * Builder for endpoint producers for the Hazelcast Multimap component.
     */
    public interface HazelcastMultimapEndpointProducerBuilder
            extends
                EndpointProducerBuilder {
        default AdvancedHazelcastMultimapEndpointProducerBuilder advanced() {
            return (AdvancedHazelcastMultimapEndpointProducerBuilder) this;
        }
        /**
         * To specify a default operation to use, if no operation header has
         * been provided.
         * 
         * The option is a:
         * &lt;code&gt;org.apache.camel.component.hazelcast.HazelcastOperation&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param defaultOperation the value to set
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointProducerBuilder defaultOperation(
                org.apache.camel.component.hazelcast.HazelcastOperation defaultOperation) {
            doSetProperty("defaultOperation", defaultOperation);
            return this;
        }
        /**
         * To specify a default operation to use, if no operation header has
         * been provided.
         * 
         * The option will be converted to a
         * &lt;code&gt;org.apache.camel.component.hazelcast.HazelcastOperation&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param defaultOperation the value to set
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointProducerBuilder defaultOperation(
                String defaultOperation) {
            doSetProperty("defaultOperation", defaultOperation);
            return this;
        }
        /**
         * The hazelcast instance reference which can be used for hazelcast
         * endpoint.
         * 
         * The option is a:
         * &lt;code&gt;com.hazelcast.core.HazelcastInstance&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param hazelcastInstance the value to set
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointProducerBuilder hazelcastInstance(
                com.hazelcast.core.HazelcastInstance hazelcastInstance) {
            doSetProperty("hazelcastInstance", hazelcastInstance);
            return this;
        }
        /**
         * The hazelcast instance reference which can be used for hazelcast
         * endpoint.
         * 
         * The option will be converted to a
         * &lt;code&gt;com.hazelcast.core.HazelcastInstance&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param hazelcastInstance the value to set
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointProducerBuilder hazelcastInstance(
                String hazelcastInstance) {
            doSetProperty("hazelcastInstance", hazelcastInstance);
            return this;
        }
        /**
         * The hazelcast instance reference name which can be used for hazelcast
         * endpoint. If you don't specify the instance reference, camel use the
         * default hazelcast instance from the camel-hazelcast instance.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param hazelcastInstanceName the value to set
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointProducerBuilder hazelcastInstanceName(
                String hazelcastInstanceName) {
            doSetProperty("hazelcastInstanceName", hazelcastInstanceName);
            return this;
        }
    }

    /**
     * Advanced builder for endpoint producers for the Hazelcast Multimap
     * component.
     */
    public interface AdvancedHazelcastMultimapEndpointProducerBuilder
            extends
                EndpointProducerBuilder {
        default HazelcastMultimapEndpointProducerBuilder basic() {
            return (HazelcastMultimapEndpointProducerBuilder) this;
        }
        /**
         * Whether the producer should be started lazy (on the first message).
         * By starting lazy you can use this to allow CamelContext and routes to
         * startup in situations where a producer may otherwise fail during
         * starting and cause the route to fail being started. By deferring this
         * startup to be lazy then the startup failure can be handled during
         * routing messages via Camel's routing error handlers. Beware that when
         * the first message is processed then creating and starting the
         * producer may take a little time and prolong the total processing time
         * of the processing.
         * 
         * The option is a: &lt;code&gt;boolean&lt;/code&gt; type.
         * 
         * Default: false
         * Group: producer (advanced)
         * 
         * @param lazyStartProducer the value to set
         * @return the dsl builder
         */
        default AdvancedHazelcastMultimapEndpointProducerBuilder lazyStartProducer(
                boolean lazyStartProducer) {
            doSetProperty("lazyStartProducer", lazyStartProducer);
            return this;
        }
        /**
         * Whether the producer should be started lazy (on the first message).
         * By starting lazy you can use this to allow CamelContext and routes to
         * startup in situations where a producer may otherwise fail during
         * starting and cause the route to fail being started. By deferring this
         * startup to be lazy then the startup failure can be handled during
         * routing messages via Camel's routing error handlers. Beware that when
         * the first message is processed then creating and starting the
         * producer may take a little time and prolong the total processing time
         * of the processing.
         * 
         * The option will be converted to a &lt;code&gt;boolean&lt;/code&gt;
         * type.
         * 
         * Default: false
         * Group: producer (advanced)
         * 
         * @param lazyStartProducer the value to set
         * @return the dsl builder
         */
        default AdvancedHazelcastMultimapEndpointProducerBuilder lazyStartProducer(
                String lazyStartProducer) {
            doSetProperty("lazyStartProducer", lazyStartProducer);
            return this;
        }
    }

    /**
     * Builder for endpoint for the Hazelcast Multimap component.
     */
    public interface HazelcastMultimapEndpointBuilder
            extends
                HazelcastMultimapEndpointConsumerBuilder,
                HazelcastMultimapEndpointProducerBuilder {
        default AdvancedHazelcastMultimapEndpointBuilder advanced() {
            return (AdvancedHazelcastMultimapEndpointBuilder) this;
        }
        /**
         * To specify a default operation to use, if no operation header has
         * been provided.
         * 
         * The option is a:
         * &lt;code&gt;org.apache.camel.component.hazelcast.HazelcastOperation&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param defaultOperation the value to set
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointBuilder defaultOperation(
                org.apache.camel.component.hazelcast.HazelcastOperation defaultOperation) {
            doSetProperty("defaultOperation", defaultOperation);
            return this;
        }
        /**
         * To specify a default operation to use, if no operation header has
         * been provided.
         * 
         * The option will be converted to a
         * &lt;code&gt;org.apache.camel.component.hazelcast.HazelcastOperation&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param defaultOperation the value to set
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointBuilder defaultOperation(
                String defaultOperation) {
            doSetProperty("defaultOperation", defaultOperation);
            return this;
        }
        /**
         * The hazelcast instance reference which can be used for hazelcast
         * endpoint.
         * 
         * The option is a:
         * &lt;code&gt;com.hazelcast.core.HazelcastInstance&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param hazelcastInstance the value to set
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointBuilder hazelcastInstance(
                com.hazelcast.core.HazelcastInstance hazelcastInstance) {
            doSetProperty("hazelcastInstance", hazelcastInstance);
            return this;
        }
        /**
         * The hazelcast instance reference which can be used for hazelcast
         * endpoint.
         * 
         * The option will be converted to a
         * &lt;code&gt;com.hazelcast.core.HazelcastInstance&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param hazelcastInstance the value to set
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointBuilder hazelcastInstance(
                String hazelcastInstance) {
            doSetProperty("hazelcastInstance", hazelcastInstance);
            return this;
        }
        /**
         * The hazelcast instance reference name which can be used for hazelcast
         * endpoint. If you don't specify the instance reference, camel use the
         * default hazelcast instance from the camel-hazelcast instance.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param hazelcastInstanceName the value to set
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointBuilder hazelcastInstanceName(
                String hazelcastInstanceName) {
            doSetProperty("hazelcastInstanceName", hazelcastInstanceName);
            return this;
        }
    }

    /**
     * Advanced builder for endpoint for the Hazelcast Multimap component.
     */
    public interface AdvancedHazelcastMultimapEndpointBuilder
            extends
                AdvancedHazelcastMultimapEndpointConsumerBuilder,
                AdvancedHazelcastMultimapEndpointProducerBuilder {
        default HazelcastMultimapEndpointBuilder basic() {
            return (HazelcastMultimapEndpointBuilder) this;
        }
    }

    public interface HazelcastMultimapBuilders {
        /**
         * Hazelcast Multimap (camel-hazelcast)
         * Perform operations on Hazelcast distributed multimap.
         * 
         * Category: cache,datagrid
         * Since: 2.7
         * Maven coordinates: org.apache.camel:camel-hazelcast
         * 
         * @return the dsl builder for the headers' name.
         */
        default HazelcastMultimapHeaderNameBuilder hazelcastMultimap() {
            return HazelcastMultimapHeaderNameBuilder.INSTANCE;
        }
        /**
         * Hazelcast Multimap (camel-hazelcast)
         * Perform operations on Hazelcast distributed multimap.
         * 
         * Category: cache,datagrid
         * Since: 2.7
         * Maven coordinates: org.apache.camel:camel-hazelcast
         * 
         * Syntax: <code>hazelcast-multimap:cacheName</code>
         * 
         * Path parameter: cacheName (required)
         * The name of the cache
         * 
         * @param path cacheName
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointBuilder hazelcastMultimap(String path) {
            return HazelcastMultimapEndpointBuilderFactory.endpointBuilder("hazelcast-multimap", path);
        }
        /**
         * Hazelcast Multimap (camel-hazelcast)
         * Perform operations on Hazelcast distributed multimap.
         * 
         * Category: cache,datagrid
         * Since: 2.7
         * Maven coordinates: org.apache.camel:camel-hazelcast
         * 
         * Syntax: <code>hazelcast-multimap:cacheName</code>
         * 
         * Path parameter: cacheName (required)
         * The name of the cache
         * 
         * @param componentName to use a custom component name for the endpoint
         * instead of the default name
         * @param path cacheName
         * @return the dsl builder
         */
        default HazelcastMultimapEndpointBuilder hazelcastMultimap(
                String componentName,
                String path) {
            return HazelcastMultimapEndpointBuilderFactory.endpointBuilder(componentName, path);
        }
    }

    /**
     * The builder of headers' name for the Hazelcast Multimap component.
     */
    public static class HazelcastMultimapHeaderNameBuilder {
        /**
         * The internal instance of the builder used to access to all the
         * methods representing the name of headers.
         */
        private static final HazelcastMultimapHeaderNameBuilder INSTANCE = new HazelcastMultimapHeaderNameBuilder();

        /**
         * the object id to store / find your object inside the cache.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: common
         * 
         * @return the name of the header {@code HazelcastObjectId}.
         */
        public String hazelcastObjectId() {
            return "HazelcastObjectId";
        }

        /**
         * The type of event - here added and removed.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: consumer
         * 
         * @return the name of the header {@code HazelcastListenerAction}.
         */
        public String hazelcastListenerAction() {
            return "HazelcastListenerAction";
        }

        /**
         * The map consumer.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: consumer
         * 
         * @return the name of the header {@code HazelcastListenerType}.
         */
        public String hazelcastListenerType() {
            return "HazelcastListenerType";
        }

        /**
         * The time of the event in millis.
         * 
         * The option is a: {@code Long} type.
         * 
         * Group: consumer
         * 
         * @return the name of the header {@code HazelcastListenerTime}.
         */
        public String hazelcastListenerTime() {
            return "HazelcastListenerTime";
        }

        /**
         * The name of the cache - e.g. foo.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: consumer
         * 
         * @return the name of the header {@code HazelcastCacheName}.
         */
        public String hazelcastCacheName() {
            return "HazelcastCacheName";
        }

        /**
         * The type of the cache - here multimap.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: common
         * 
         * @return the name of the header {@code HazelcastCacheType}.
         */
        public String hazelcastCacheType() {
            return "HazelcastCacheType";
        }

        /**
         * The operation to perform.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: producer
         * 
         * @return the name of the header {@code HazelcastOperationType}.
         */
        public String hazelcastOperationType() {
            return "HazelcastOperationType";
        }
    }
    static HazelcastMultimapEndpointBuilder endpointBuilder(
            String componentName,
            String path) {
        class HazelcastMultimapEndpointBuilderImpl extends AbstractEndpointBuilder implements HazelcastMultimapEndpointBuilder, AdvancedHazelcastMultimapEndpointBuilder {
            public HazelcastMultimapEndpointBuilderImpl(String path) {
                super(componentName, path);
            }
        }
        return new HazelcastMultimapEndpointBuilderImpl(path);
    }
}