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
package org.apache.camel.converter.jaxb.message;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the
 * org.apache.camel.converter.jaxb.message package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation for XML content.
 * The Java representation of XML content can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory methods for each of these are provided in
 * this class.
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName MESSAGE_QNAME = new QName("message.jaxb.converter.camel.apache.org", "message");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package:
     * org.apache.camel.converter.jaxb.message
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Message }
     */
    public Message createMessage() {
        return new Message();
    }

    @XmlElementDecl(namespace = "message.jaxb.converter.camel.apache.org", name = "message")
    public JAXBElement<Message> createMessage(Message value) {
        return new JAXBElement<>(MESSAGE_QNAME, Message.class, null, value);
    }

}
