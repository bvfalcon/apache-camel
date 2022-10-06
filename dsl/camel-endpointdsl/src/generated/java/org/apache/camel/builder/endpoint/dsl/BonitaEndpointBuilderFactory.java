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
 * Communicate with a remote Bonita BPM process engine.
 * 
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.EndpointDslMojo")
public interface BonitaEndpointBuilderFactory {


    /**
     * Builder for endpoint for the Bonita component.
     */
    public interface BonitaEndpointBuilder extends EndpointProducerBuilder {
        default AdvancedBonitaEndpointBuilder advanced() {
            return (AdvancedBonitaEndpointBuilder) this;
        }
        /**
         * Hostname where Bonita engine runs.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Default: localhost
         * Group: producer
         * 
         * @param hostname the value to set
         * @return the dsl builder
         */
        default BonitaEndpointBuilder hostname(String hostname) {
            doSetProperty("hostname", hostname);
            return this;
        }
        /**
         * Port of the server hosting Bonita engine.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Default: 8080
         * Group: producer
         * 
         * @param port the value to set
         * @return the dsl builder
         */
        default BonitaEndpointBuilder port(String port) {
            doSetProperty("port", port);
            return this;
        }
        /**
         * Name of the process involved in the operation.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: producer
         * 
         * @param processName the value to set
         * @return the dsl builder
         */
        default BonitaEndpointBuilder processName(String processName) {
            doSetProperty("processName", processName);
            return this;
        }
        /**
         * Password to authenticate to Bonita engine.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: security
         * 
         * @param password the value to set
         * @return the dsl builder
         */
        default BonitaEndpointBuilder password(String password) {
            doSetProperty("password", password);
            return this;
        }
        /**
         * Username to authenticate to Bonita engine.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: security
         * 
         * @param username the value to set
         * @return the dsl builder
         */
        default BonitaEndpointBuilder username(String username) {
            doSetProperty("username", username);
            return this;
        }
    }

    /**
     * Advanced builder for endpoint for the Bonita component.
     */
    public interface AdvancedBonitaEndpointBuilder
            extends
                EndpointProducerBuilder {
        default BonitaEndpointBuilder basic() {
            return (BonitaEndpointBuilder) this;
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
        default AdvancedBonitaEndpointBuilder lazyStartProducer(
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
        default AdvancedBonitaEndpointBuilder lazyStartProducer(
                String lazyStartProducer) {
            doSetProperty("lazyStartProducer", lazyStartProducer);
            return this;
        }
    }

    public interface BonitaBuilders {
        /**
         * Bonita (camel-bonita)
         * Communicate with a remote Bonita BPM process engine.
         * 
         * Category: process
         * Since: 2.19
         * Maven coordinates: org.apache.camel:camel-bonita
         * 
         * Syntax: <code>bonita:operation</code>
         * 
         * Path parameter: operation (required)
         * Operation to use
         * There are 1 enums and the value can be one of: startCase
         * 
         * @param path operation
         * @return the dsl builder
         */
        default BonitaEndpointBuilder bonita(String path) {
            return BonitaEndpointBuilderFactory.endpointBuilder("bonita", path);
        }
        /**
         * Bonita (camel-bonita)
         * Communicate with a remote Bonita BPM process engine.
         * 
         * Category: process
         * Since: 2.19
         * Maven coordinates: org.apache.camel:camel-bonita
         * 
         * Syntax: <code>bonita:operation</code>
         * 
         * Path parameter: operation (required)
         * Operation to use
         * There are 1 enums and the value can be one of: startCase
         * 
         * @param componentName to use a custom component name for the endpoint
         * instead of the default name
         * @param path operation
         * @return the dsl builder
         */
        default BonitaEndpointBuilder bonita(String componentName, String path) {
            return BonitaEndpointBuilderFactory.endpointBuilder(componentName, path);
        }
    }
    static BonitaEndpointBuilder endpointBuilder(
            String componentName,
            String path) {
        class BonitaEndpointBuilderImpl extends AbstractEndpointBuilder implements BonitaEndpointBuilder, AdvancedBonitaEndpointBuilder {
            public BonitaEndpointBuilderImpl(String path) {
                super(componentName, path);
            }
        }
        return new BonitaEndpointBuilderImpl(path);
    }
}