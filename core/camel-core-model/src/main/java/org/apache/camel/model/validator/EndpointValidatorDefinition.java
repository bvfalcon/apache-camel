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
package org.apache.camel.model.validator;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.Validator;

/**
 * Represents an endpoint {@link Validator} which leverages camel validator component such as
 * <a href="http://camel.apache.org/validation.html">Validator Component</a> and
 * <a href="http://camel.apache.org/bean-validation.html">Bean Validator Component</a> to perform content validation. A
 * {@link org.apache.camel.processor.validator.ProcessorValidator} will be created internally with a
 * {@link org.apache.camel.processor.SendProcessor} which forwards the message to the validator Endpoint.
 * {@see ValidatorDefinition} {@see Validator}
 */
@Metadata(label = "validation")
@XmlType(name = "endpointValidator")
@XmlAccessorType(XmlAccessType.FIELD)
public class EndpointValidatorDefinition extends ValidatorDefinition {

    @XmlAttribute
    private String ref;
    @XmlAttribute
    private String uri;

    public String getRef() {
        return ref;
    }

    /**
     * Set the reference of the Endpoint.
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getUri() {
        return uri;
    }

    /**
     * Set the URI of the Endpoint.
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

}
