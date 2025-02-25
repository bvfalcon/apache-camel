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
package org.apache.camel.component.cxf.mtom;

import java.net.URL;

import jakarta.xml.ws.BindingProvider;
import org.apache.camel.cxf.mtom_feature.Hello;
import org.apache.camel.cxf.mtom_feature.HelloService;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration
public class CxfMtomRouterCxfMessageModeTest extends CxfMtomRouterPayloadModeTest {
    @Override
    protected Hello getPort() {
        URL wsdl = getClass().getResource("/mtom.wsdl");
        assertNotNull(wsdl, "WSDL is null");

        HelloService service = new HelloService(wsdl, HelloService.SERVICE);
        assertNotNull(service, "Service is null");
        Hello port = service.getHelloPort();
        ((BindingProvider) port).getRequestContext()
                .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                        "http://localhost:" + port1 + "/CxfMtomRouterCxfMessageModeTest/jaxws-mtom/hello");
        return port;
    }

}
