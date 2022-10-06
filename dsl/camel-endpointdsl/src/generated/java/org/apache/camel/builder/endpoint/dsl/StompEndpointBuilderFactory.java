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
 * Send and rececive messages to/from STOMP (Simple Text Oriented Messaging
 * Protocol) compliant message brokers.
 * 
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.EndpointDslMojo")
public interface StompEndpointBuilderFactory {


    /**
     * Builder for endpoint consumers for the Stomp component.
     */
    public interface StompEndpointConsumerBuilder
            extends
                EndpointConsumerBuilder {
        default AdvancedStompEndpointConsumerBuilder advanced() {
            return (AdvancedStompEndpointConsumerBuilder) this;
        }
        /**
         * The URI of the Stomp broker to connect to.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Required: true
         * Default: tcp://localhost:61613
         * Group: common
         * 
         * @param brokerURL the value to set
         * @return the dsl builder
         */
        default StompEndpointConsumerBuilder brokerURL(String brokerURL) {
            doSetProperty("brokerURL", brokerURL);
            return this;
        }
        /**
         * To set custom headers.
         * 
         * The option is a: &lt;code&gt;java.util.Properties&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param customHeaders the value to set
         * @return the dsl builder
         */
        default StompEndpointConsumerBuilder customHeaders(
                Properties customHeaders) {
            doSetProperty("customHeaders", customHeaders);
            return this;
        }
        /**
         * To set custom headers.
         * 
         * The option will be converted to a
         * &lt;code&gt;java.util.Properties&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param customHeaders the value to set
         * @return the dsl builder
         */
        default StompEndpointConsumerBuilder customHeaders(String customHeaders) {
            doSetProperty("customHeaders", customHeaders);
            return this;
        }
        /**
         * The virtual host name.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param host the value to set
         * @return the dsl builder
         */
        default StompEndpointConsumerBuilder host(String host) {
            doSetProperty("host", host);
            return this;
        }
        /**
         * The stomp version (1.1, or 1.2).
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param version the value to set
         * @return the dsl builder
         */
        default StompEndpointConsumerBuilder version(String version) {
            doSetProperty("version", version);
            return this;
        }
        /**
         * The username.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: security
         * 
         * @param login the value to set
         * @return the dsl builder
         */
        default StompEndpointConsumerBuilder login(String login) {
            doSetProperty("login", login);
            return this;
        }
        /**
         * The password.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: security
         * 
         * @param passcode the value to set
         * @return the dsl builder
         */
        default StompEndpointConsumerBuilder passcode(String passcode) {
            doSetProperty("passcode", passcode);
            return this;
        }
        /**
         * To configure security using SSLContextParameters.
         * 
         * The option is a:
         * &lt;code&gt;org.apache.camel.support.jsse.SSLContextParameters&lt;/code&gt; type.
         * 
         * Group: security
         * 
         * @param sslContextParameters the value to set
         * @return the dsl builder
         */
        default StompEndpointConsumerBuilder sslContextParameters(
                org.apache.camel.support.jsse.SSLContextParameters sslContextParameters) {
            doSetProperty("sslContextParameters", sslContextParameters);
            return this;
        }
        /**
         * To configure security using SSLContextParameters.
         * 
         * The option will be converted to a
         * &lt;code&gt;org.apache.camel.support.jsse.SSLContextParameters&lt;/code&gt; type.
         * 
         * Group: security
         * 
         * @param sslContextParameters the value to set
         * @return the dsl builder
         */
        default StompEndpointConsumerBuilder sslContextParameters(
                String sslContextParameters) {
            doSetProperty("sslContextParameters", sslContextParameters);
            return this;
        }
    }

    /**
     * Advanced builder for endpoint consumers for the Stomp component.
     */
    public interface AdvancedStompEndpointConsumerBuilder
            extends
                EndpointConsumerBuilder {
        default StompEndpointConsumerBuilder basic() {
            return (StompEndpointConsumerBuilder) this;
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
        default AdvancedStompEndpointConsumerBuilder bridgeErrorHandler(
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
        default AdvancedStompEndpointConsumerBuilder bridgeErrorHandler(
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
        default AdvancedStompEndpointConsumerBuilder exceptionHandler(
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
        default AdvancedStompEndpointConsumerBuilder exceptionHandler(
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
        default AdvancedStompEndpointConsumerBuilder exchangePattern(
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
        default AdvancedStompEndpointConsumerBuilder exchangePattern(
                String exchangePattern) {
            doSetProperty("exchangePattern", exchangePattern);
            return this;
        }
        /**
         * To use a custom HeaderFilterStrategy to filter header to and from
         * Camel message.
         * 
         * The option is a:
         * &lt;code&gt;org.apache.camel.spi.HeaderFilterStrategy&lt;/code&gt;
         * type.
         * 
         * Group: advanced
         * 
         * @param headerFilterStrategy the value to set
         * @return the dsl builder
         */
        default AdvancedStompEndpointConsumerBuilder headerFilterStrategy(
                org.apache.camel.spi.HeaderFilterStrategy headerFilterStrategy) {
            doSetProperty("headerFilterStrategy", headerFilterStrategy);
            return this;
        }
        /**
         * To use a custom HeaderFilterStrategy to filter header to and from
         * Camel message.
         * 
         * The option will be converted to a
         * &lt;code&gt;org.apache.camel.spi.HeaderFilterStrategy&lt;/code&gt;
         * type.
         * 
         * Group: advanced
         * 
         * @param headerFilterStrategy the value to set
         * @return the dsl builder
         */
        default AdvancedStompEndpointConsumerBuilder headerFilterStrategy(
                String headerFilterStrategy) {
            doSetProperty("headerFilterStrategy", headerFilterStrategy);
            return this;
        }
    }

    /**
     * Builder for endpoint producers for the Stomp component.
     */
    public interface StompEndpointProducerBuilder
            extends
                EndpointProducerBuilder {
        default AdvancedStompEndpointProducerBuilder advanced() {
            return (AdvancedStompEndpointProducerBuilder) this;
        }
        /**
         * The URI of the Stomp broker to connect to.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Required: true
         * Default: tcp://localhost:61613
         * Group: common
         * 
         * @param brokerURL the value to set
         * @return the dsl builder
         */
        default StompEndpointProducerBuilder brokerURL(String brokerURL) {
            doSetProperty("brokerURL", brokerURL);
            return this;
        }
        /**
         * To set custom headers.
         * 
         * The option is a: &lt;code&gt;java.util.Properties&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param customHeaders the value to set
         * @return the dsl builder
         */
        default StompEndpointProducerBuilder customHeaders(
                Properties customHeaders) {
            doSetProperty("customHeaders", customHeaders);
            return this;
        }
        /**
         * To set custom headers.
         * 
         * The option will be converted to a
         * &lt;code&gt;java.util.Properties&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param customHeaders the value to set
         * @return the dsl builder
         */
        default StompEndpointProducerBuilder customHeaders(String customHeaders) {
            doSetProperty("customHeaders", customHeaders);
            return this;
        }
        /**
         * The virtual host name.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param host the value to set
         * @return the dsl builder
         */
        default StompEndpointProducerBuilder host(String host) {
            doSetProperty("host", host);
            return this;
        }
        /**
         * The stomp version (1.1, or 1.2).
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param version the value to set
         * @return the dsl builder
         */
        default StompEndpointProducerBuilder version(String version) {
            doSetProperty("version", version);
            return this;
        }
        /**
         * The username.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: security
         * 
         * @param login the value to set
         * @return the dsl builder
         */
        default StompEndpointProducerBuilder login(String login) {
            doSetProperty("login", login);
            return this;
        }
        /**
         * The password.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: security
         * 
         * @param passcode the value to set
         * @return the dsl builder
         */
        default StompEndpointProducerBuilder passcode(String passcode) {
            doSetProperty("passcode", passcode);
            return this;
        }
        /**
         * To configure security using SSLContextParameters.
         * 
         * The option is a:
         * &lt;code&gt;org.apache.camel.support.jsse.SSLContextParameters&lt;/code&gt; type.
         * 
         * Group: security
         * 
         * @param sslContextParameters the value to set
         * @return the dsl builder
         */
        default StompEndpointProducerBuilder sslContextParameters(
                org.apache.camel.support.jsse.SSLContextParameters sslContextParameters) {
            doSetProperty("sslContextParameters", sslContextParameters);
            return this;
        }
        /**
         * To configure security using SSLContextParameters.
         * 
         * The option will be converted to a
         * &lt;code&gt;org.apache.camel.support.jsse.SSLContextParameters&lt;/code&gt; type.
         * 
         * Group: security
         * 
         * @param sslContextParameters the value to set
         * @return the dsl builder
         */
        default StompEndpointProducerBuilder sslContextParameters(
                String sslContextParameters) {
            doSetProperty("sslContextParameters", sslContextParameters);
            return this;
        }
    }

    /**
     * Advanced builder for endpoint producers for the Stomp component.
     */
    public interface AdvancedStompEndpointProducerBuilder
            extends
                EndpointProducerBuilder {
        default StompEndpointProducerBuilder basic() {
            return (StompEndpointProducerBuilder) this;
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
        default AdvancedStompEndpointProducerBuilder lazyStartProducer(
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
        default AdvancedStompEndpointProducerBuilder lazyStartProducer(
                String lazyStartProducer) {
            doSetProperty("lazyStartProducer", lazyStartProducer);
            return this;
        }
        /**
         * To use a custom HeaderFilterStrategy to filter header to and from
         * Camel message.
         * 
         * The option is a:
         * &lt;code&gt;org.apache.camel.spi.HeaderFilterStrategy&lt;/code&gt;
         * type.
         * 
         * Group: advanced
         * 
         * @param headerFilterStrategy the value to set
         * @return the dsl builder
         */
        default AdvancedStompEndpointProducerBuilder headerFilterStrategy(
                org.apache.camel.spi.HeaderFilterStrategy headerFilterStrategy) {
            doSetProperty("headerFilterStrategy", headerFilterStrategy);
            return this;
        }
        /**
         * To use a custom HeaderFilterStrategy to filter header to and from
         * Camel message.
         * 
         * The option will be converted to a
         * &lt;code&gt;org.apache.camel.spi.HeaderFilterStrategy&lt;/code&gt;
         * type.
         * 
         * Group: advanced
         * 
         * @param headerFilterStrategy the value to set
         * @return the dsl builder
         */
        default AdvancedStompEndpointProducerBuilder headerFilterStrategy(
                String headerFilterStrategy) {
            doSetProperty("headerFilterStrategy", headerFilterStrategy);
            return this;
        }
    }

    /**
     * Builder for endpoint for the Stomp component.
     */
    public interface StompEndpointBuilder
            extends
                StompEndpointConsumerBuilder,
                StompEndpointProducerBuilder {
        default AdvancedStompEndpointBuilder advanced() {
            return (AdvancedStompEndpointBuilder) this;
        }
        /**
         * The URI of the Stomp broker to connect to.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Required: true
         * Default: tcp://localhost:61613
         * Group: common
         * 
         * @param brokerURL the value to set
         * @return the dsl builder
         */
        default StompEndpointBuilder brokerURL(String brokerURL) {
            doSetProperty("brokerURL", brokerURL);
            return this;
        }
        /**
         * To set custom headers.
         * 
         * The option is a: &lt;code&gt;java.util.Properties&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param customHeaders the value to set
         * @return the dsl builder
         */
        default StompEndpointBuilder customHeaders(Properties customHeaders) {
            doSetProperty("customHeaders", customHeaders);
            return this;
        }
        /**
         * To set custom headers.
         * 
         * The option will be converted to a
         * &lt;code&gt;java.util.Properties&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param customHeaders the value to set
         * @return the dsl builder
         */
        default StompEndpointBuilder customHeaders(String customHeaders) {
            doSetProperty("customHeaders", customHeaders);
            return this;
        }
        /**
         * The virtual host name.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param host the value to set
         * @return the dsl builder
         */
        default StompEndpointBuilder host(String host) {
            doSetProperty("host", host);
            return this;
        }
        /**
         * The stomp version (1.1, or 1.2).
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: common
         * 
         * @param version the value to set
         * @return the dsl builder
         */
        default StompEndpointBuilder version(String version) {
            doSetProperty("version", version);
            return this;
        }
        /**
         * The username.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: security
         * 
         * @param login the value to set
         * @return the dsl builder
         */
        default StompEndpointBuilder login(String login) {
            doSetProperty("login", login);
            return this;
        }
        /**
         * The password.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: security
         * 
         * @param passcode the value to set
         * @return the dsl builder
         */
        default StompEndpointBuilder passcode(String passcode) {
            doSetProperty("passcode", passcode);
            return this;
        }
        /**
         * To configure security using SSLContextParameters.
         * 
         * The option is a:
         * &lt;code&gt;org.apache.camel.support.jsse.SSLContextParameters&lt;/code&gt; type.
         * 
         * Group: security
         * 
         * @param sslContextParameters the value to set
         * @return the dsl builder
         */
        default StompEndpointBuilder sslContextParameters(
                org.apache.camel.support.jsse.SSLContextParameters sslContextParameters) {
            doSetProperty("sslContextParameters", sslContextParameters);
            return this;
        }
        /**
         * To configure security using SSLContextParameters.
         * 
         * The option will be converted to a
         * &lt;code&gt;org.apache.camel.support.jsse.SSLContextParameters&lt;/code&gt; type.
         * 
         * Group: security
         * 
         * @param sslContextParameters the value to set
         * @return the dsl builder
         */
        default StompEndpointBuilder sslContextParameters(
                String sslContextParameters) {
            doSetProperty("sslContextParameters", sslContextParameters);
            return this;
        }
    }

    /**
     * Advanced builder for endpoint for the Stomp component.
     */
    public interface AdvancedStompEndpointBuilder
            extends
                AdvancedStompEndpointConsumerBuilder,
                AdvancedStompEndpointProducerBuilder {
        default StompEndpointBuilder basic() {
            return (StompEndpointBuilder) this;
        }
        /**
         * To use a custom HeaderFilterStrategy to filter header to and from
         * Camel message.
         * 
         * The option is a:
         * &lt;code&gt;org.apache.camel.spi.HeaderFilterStrategy&lt;/code&gt;
         * type.
         * 
         * Group: advanced
         * 
         * @param headerFilterStrategy the value to set
         * @return the dsl builder
         */
        default AdvancedStompEndpointBuilder headerFilterStrategy(
                org.apache.camel.spi.HeaderFilterStrategy headerFilterStrategy) {
            doSetProperty("headerFilterStrategy", headerFilterStrategy);
            return this;
        }
        /**
         * To use a custom HeaderFilterStrategy to filter header to and from
         * Camel message.
         * 
         * The option will be converted to a
         * &lt;code&gt;org.apache.camel.spi.HeaderFilterStrategy&lt;/code&gt;
         * type.
         * 
         * Group: advanced
         * 
         * @param headerFilterStrategy the value to set
         * @return the dsl builder
         */
        default AdvancedStompEndpointBuilder headerFilterStrategy(
                String headerFilterStrategy) {
            doSetProperty("headerFilterStrategy", headerFilterStrategy);
            return this;
        }
    }

    public interface StompBuilders {
        /**
         * Stomp (camel-stomp)
         * Send and rececive messages to/from STOMP (Simple Text Oriented
         * Messaging Protocol) compliant message brokers.
         * 
         * Category: messaging
         * Since: 2.12
         * Maven coordinates: org.apache.camel:camel-stomp
         * 
         * Syntax: <code>stomp:destination</code>
         * 
         * Path parameter: destination (required)
         * Name of the queue
         * 
         * @param path destination
         * @return the dsl builder
         */
        default StompEndpointBuilder stomp(String path) {
            return StompEndpointBuilderFactory.endpointBuilder("stomp", path);
        }
        /**
         * Stomp (camel-stomp)
         * Send and rececive messages to/from STOMP (Simple Text Oriented
         * Messaging Protocol) compliant message brokers.
         * 
         * Category: messaging
         * Since: 2.12
         * Maven coordinates: org.apache.camel:camel-stomp
         * 
         * Syntax: <code>stomp:destination</code>
         * 
         * Path parameter: destination (required)
         * Name of the queue
         * 
         * @param componentName to use a custom component name for the endpoint
         * instead of the default name
         * @param path destination
         * @return the dsl builder
         */
        default StompEndpointBuilder stomp(String componentName, String path) {
            return StompEndpointBuilderFactory.endpointBuilder(componentName, path);
        }
    }
    static StompEndpointBuilder endpointBuilder(
            String componentName,
            String path) {
        class StompEndpointBuilderImpl extends AbstractEndpointBuilder implements StompEndpointBuilder, AdvancedStompEndpointBuilder {
            public StompEndpointBuilderImpl(String path) {
                super(componentName, path);
            }
        }
        return new StompEndpointBuilderImpl(path);
    }
}