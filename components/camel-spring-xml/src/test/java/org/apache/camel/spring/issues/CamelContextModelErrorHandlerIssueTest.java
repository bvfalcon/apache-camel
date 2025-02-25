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
package org.apache.camel.spring.issues;

import java.io.File;
import java.io.StringWriter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.camel.spring.xml.CamelContextFactoryBean;
import org.apache.camel.spring.xml.SpringModelJAXBContextFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 */
public class CamelContextModelErrorHandlerIssueTest {

    private static final Logger LOG = LoggerFactory.getLogger(CamelContextModelErrorHandlerIssueTest.class);

    @Test
    public void testCamelContextModel() throws Exception {
        JAXBContext jaxbContext = new SpringModelJAXBContextFactory().newJAXBContext();

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Object obj = unmarshaller.unmarshal(
                new File("src/test/resources/org/apache/camel/spring/issues/CamelContextModelErrorHandlerIssueTest.xml"));
        assertNotNull(obj);

        CamelContextFactoryBean context = (CamelContextFactoryBean) obj;

        assertEquals("myCamel", context.getId());
        assertEquals("dlc", context.getErrorHandlerRef());
        assertEquals(1, context.getRoutes().size());

        Marshaller marshaller = jaxbContext.createMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(context, writer);

        String s = writer.getBuffer().toString();
        LOG.info(s);
        assertTrue(s.contains("<errorHandler"), "Should have error handler");
        assertTrue(s.contains("<redeliveryPolicy"), "Should have redelivery policy");
    }
}
