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
package org.apache.camel.component.bonita.api;

import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.core.Cookie;
import org.apache.camel.component.bonita.api.filter.BonitaAuthFilter;
import org.apache.camel.component.bonita.api.util.BonitaAPIConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BonitaAuthFilterTest {

    @Mock
    private ClientRequestContext requestContext;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Map<String, Cookie> resultCookies = new HashMap<>();
        Mockito.when(requestContext.getCookies()).thenReturn(resultCookies);

    }

    @Test
    public void testBonitaAuthFilterUsernameEmpty() {
        BonitaAPIConfig bonitaApiConfig = new BonitaAPIConfig("localhost", "port", "", "password");
        BonitaAuthFilter bonitaAuthFilter = new BonitaAuthFilter(bonitaApiConfig);
        assertThrows(IllegalArgumentException.class,
                () -> bonitaAuthFilter.filter(requestContext));
    }

    @Test
    public void testBonitaAuthFilterPasswordEmpty() {
        BonitaAPIConfig bonitaApiConfig = new BonitaAPIConfig("localhost", "port", "username", "");
        BonitaAuthFilter bonitaAuthFilter = new BonitaAuthFilter(bonitaApiConfig);
        assertThrows(IllegalArgumentException.class,
                () -> bonitaAuthFilter.filter(requestContext));
    }

}
