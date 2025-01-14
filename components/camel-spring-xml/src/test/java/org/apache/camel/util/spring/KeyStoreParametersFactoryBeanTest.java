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
package org.apache.camel.util.spring;

import jakarta.annotation.Resource;
import org.apache.camel.spring.xml.KeyStoreParametersFactoryBean;
import org.apache.camel.support.jsse.KeyStoreParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class KeyStoreParametersFactoryBeanTest {

    @Resource
    KeyStoreParameters ksp;

    @Resource(name = "&ksp")
    KeyStoreParametersFactoryBean kspfb;

    @Test
    public void testKeyStoreParameters() {
        assertEquals("keystore.jks", ksp.getResource());
        assertEquals("jks", ksp.getType());
        assertEquals("provider", ksp.getProvider());
        assertEquals("password", ksp.getPassword());

        assertEquals("test", kspfb.getCamelContext().getName());
    }
}
