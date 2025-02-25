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
package org.apache.camel.component.cxf;

import jakarta.xml.ws.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.hello_world_soap_http.GreeterImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CxfGreeterCXFMessageRouterTest extends AbstractCXFGreeterRouterTest {
    protected static Endpoint endpoint;

    @AfterAll
    public static void stopService() {
        if (endpoint != null) {
            endpoint.stop();
        }
    }

    @BeforeAll
    public static void startService() {
        Object implementor = new GreeterImpl();
        String address = "http://localhost:" + getPort1()
                         + "/CxfGreeterCXFMessageRouterTest/SoapContext/SoapPort";
        endpoint = Endpoint.publish(address, implementor);
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                errorHandler(noErrorHandler());

                from("cxf:bean:routerEndpoint?dataFormat=CXF_MESSAGE&publishedEndpointUrl=http://www.simple.com/services/test")
                        .to("cxf:bean:serviceEndpoint?dataFormat=CXF_MESSAGE");
            }
        };
    }

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("org/apache/camel/component/cxf/GreeterEndpointCxfMessageBeans.xml");
    }
}
