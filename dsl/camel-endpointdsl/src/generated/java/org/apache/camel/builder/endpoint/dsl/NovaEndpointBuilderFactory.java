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
 * Access OpenStack to manage compute resources.
 * 
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.EndpointDslMojo")
public interface NovaEndpointBuilderFactory {


    /**
     * Builder for endpoint for the OpenStack Nova component.
     */
    public interface NovaEndpointBuilder extends EndpointProducerBuilder {
        default AdvancedNovaEndpointBuilder advanced() {
            return (AdvancedNovaEndpointBuilder) this;
        }
        /**
         * OpenStack API version.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Default: V3
         * Group: producer
         * 
         * @param apiVersion the value to set
         * @return the dsl builder
         */
        default NovaEndpointBuilder apiVersion(String apiVersion) {
            doSetProperty("apiVersion", apiVersion);
            return this;
        }
        /**
         * OpenStack configuration.
         * 
         * The option is a:
         * &lt;code&gt;org.openstack4j.core.transport.Config&lt;/code&gt; type.
         * 
         * Group: producer
         * 
         * @param config the value to set
         * @return the dsl builder
         */
        default NovaEndpointBuilder config(
                org.openstack4j.core.transport.Config config) {
            doSetProperty("config", config);
            return this;
        }
        /**
         * OpenStack configuration.
         * 
         * The option will be converted to a
         * &lt;code&gt;org.openstack4j.core.transport.Config&lt;/code&gt; type.
         * 
         * Group: producer
         * 
         * @param config the value to set
         * @return the dsl builder
         */
        default NovaEndpointBuilder config(String config) {
            doSetProperty("config", config);
            return this;
        }
        /**
         * Authentication domain.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Default: default
         * Group: producer
         * 
         * @param domain the value to set
         * @return the dsl builder
         */
        default NovaEndpointBuilder domain(String domain) {
            doSetProperty("domain", domain);
            return this;
        }
        /**
         * The operation to do.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Group: producer
         * 
         * @param operation the value to set
         * @return the dsl builder
         */
        default NovaEndpointBuilder operation(String operation) {
            doSetProperty("operation", operation);
            return this;
        }
        /**
         * OpenStack password.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Required: true
         * Group: producer
         * 
         * @param password the value to set
         * @return the dsl builder
         */
        default NovaEndpointBuilder password(String password) {
            doSetProperty("password", password);
            return this;
        }
        /**
         * The project ID.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Required: true
         * Group: producer
         * 
         * @param project the value to set
         * @return the dsl builder
         */
        default NovaEndpointBuilder project(String project) {
            doSetProperty("project", project);
            return this;
        }
        /**
         * OpenStack Nova subsystem.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Required: true
         * Group: producer
         * 
         * @param subsystem the value to set
         * @return the dsl builder
         */
        default NovaEndpointBuilder subsystem(String subsystem) {
            doSetProperty("subsystem", subsystem);
            return this;
        }
        /**
         * OpenStack username.
         * 
         * The option is a: &lt;code&gt;java.lang.String&lt;/code&gt; type.
         * 
         * Required: true
         * Group: producer
         * 
         * @param username the value to set
         * @return the dsl builder
         */
        default NovaEndpointBuilder username(String username) {
            doSetProperty("username", username);
            return this;
        }
    }

    /**
     * Advanced builder for endpoint for the OpenStack Nova component.
     */
    public interface AdvancedNovaEndpointBuilder
            extends
                EndpointProducerBuilder {
        default NovaEndpointBuilder basic() {
            return (NovaEndpointBuilder) this;
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
        default AdvancedNovaEndpointBuilder lazyStartProducer(
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
        default AdvancedNovaEndpointBuilder lazyStartProducer(
                String lazyStartProducer) {
            doSetProperty("lazyStartProducer", lazyStartProducer);
            return this;
        }
    }

    public interface NovaBuilders {
        /**
         * OpenStack Nova (camel-openstack)
         * Access OpenStack to manage compute resources.
         * 
         * Category: cloud,paas
         * Since: 2.19
         * Maven coordinates: org.apache.camel:camel-openstack
         * 
         * @return the dsl builder for the headers' name.
         */
        default NovaHeaderNameBuilder openstackNova() {
            return NovaHeaderNameBuilder.INSTANCE;
        }
        /**
         * OpenStack Nova (camel-openstack)
         * Access OpenStack to manage compute resources.
         * 
         * Category: cloud,paas
         * Since: 2.19
         * Maven coordinates: org.apache.camel:camel-openstack
         * 
         * Syntax: <code>openstack-nova:host</code>
         * 
         * Path parameter: host (required)
         * OpenStack host url
         * 
         * @param path host
         * @return the dsl builder
         */
        default NovaEndpointBuilder openstackNova(String path) {
            return NovaEndpointBuilderFactory.endpointBuilder("openstack-nova", path);
        }
        /**
         * OpenStack Nova (camel-openstack)
         * Access OpenStack to manage compute resources.
         * 
         * Category: cloud,paas
         * Since: 2.19
         * Maven coordinates: org.apache.camel:camel-openstack
         * 
         * Syntax: <code>openstack-nova:host</code>
         * 
         * Path parameter: host (required)
         * OpenStack host url
         * 
         * @param componentName to use a custom component name for the endpoint
         * instead of the default name
         * @param path host
         * @return the dsl builder
         */
        default NovaEndpointBuilder openstackNova(
                String componentName,
                String path) {
            return NovaEndpointBuilderFactory.endpointBuilder(componentName, path);
        }
    }

    /**
     * The builder of headers' name for the OpenStack Nova component.
     */
    public static class NovaHeaderNameBuilder {
        /**
         * The internal instance of the builder used to access to all the
         * methods representing the name of headers.
         */
        private static final NovaHeaderNameBuilder INSTANCE = new NovaHeaderNameBuilder();

        /**
         * ID of the flavor.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: flavor server
         * 
         * @return the name of the header {@code FlavorId}.
         */
        public String flavorId() {
            return "FlavorId";
        }

        /**
         * Size of RAM.
         * 
         * The option is a: {@code Integer} type.
         * 
         * Group: flavor
         * 
         * @return the name of the header {@code RAM}.
         */
        public String rAM() {
            return "RAM";
        }

        /**
         * The number of flavor VCPU.
         * 
         * The option is a: {@code Integer} type.
         * 
         * Group: flavor
         * 
         * @return the name of the header {@code VCPU}.
         */
        public String vCPU() {
            return "VCPU";
        }

        /**
         * Size of disk.
         * 
         * The option is a: {@code Integer} type.
         * 
         * Group: flavor
         * 
         * @return the name of the header {@code disk}.
         */
        public String disk() {
            return "disk";
        }

        /**
         * Size of swap.
         * 
         * The option is a: {@code Integer} type.
         * 
         * Group: flavor
         * 
         * @return the name of the header {@code swap}.
         */
        public String swap() {
            return "swap";
        }

        /**
         * Rxtx Factor.
         * 
         * The option is a: {@code Integer} type.
         * 
         * Group: flavor
         * 
         * @return the name of the header {@code rxtxFactor}.
         */
        public String rxtxFactor() {
            return "rxtxFactor";
        }

        /**
         * Admin password of the new server.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: server
         * 
         * @return the name of the header {@code AdminPassword}.
         */
        public String adminPassword() {
            return "AdminPassword";
        }

        /**
         * The Image ID.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: server
         * 
         * @return the name of the header {@code ImageId}.
         */
        public String imageId() {
            return "ImageId";
        }

        /**
         * The Keypair name.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: server
         * 
         * @return the name of the header {@code KeypairName}.
         */
        public String keypairName() {
            return "KeypairName";
        }

        /**
         * The list of networks (by id).
         * 
         * The option is a: {@code List<String>} type.
         * 
         * Group: server
         * 
         * @return the name of the header {@code NetworkId}.
         */
        public String networkId() {
            return "NetworkId";
        }

        /**
         * An action to perform.
         * 
         * The option is a: {@code org.openstack4j.model.compute.Action} type.
         * 
         * Group: server
         * 
         * @return the name of the header {@code action}.
         */
        public String action() {
            return "action";
        }

        /**
         * The operation to perform.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: producer
         * 
         * @return the name of the header {@code operation}.
         */
        public String operation() {
            return "operation";
        }

        /**
         * The ID.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: producer
         * 
         * @return the name of the header {@code ID}.
         */
        public String iD() {
            return "ID";
        }

        /**
         * The name.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: producer
         * 
         * @return the name of the header {@code name}.
         */
        public String name() {
            return "name";
        }
    }
    static NovaEndpointBuilder endpointBuilder(String componentName, String path) {
        class NovaEndpointBuilderImpl extends AbstractEndpointBuilder implements NovaEndpointBuilder, AdvancedNovaEndpointBuilder {
            public NovaEndpointBuilderImpl(String path) {
                super(componentName, path);
            }
        }
        return new NovaEndpointBuilderImpl(path);
    }
}