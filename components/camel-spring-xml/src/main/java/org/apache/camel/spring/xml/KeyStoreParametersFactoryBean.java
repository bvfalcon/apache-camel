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
package org.apache.camel.spring.xml;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import org.apache.camel.CamelContext;
import org.apache.camel.core.xml.util.jsse.AbstractKeyStoreParametersFactoryBean;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spring.util.CamelContextResolverHelper;
import org.apache.camel.support.jsse.KeyStoreParameters;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Key store facility for cryptographic keys and certificates
 */
@Metadata(label = "security,configuration")
@XmlRootElement(name = "keyStoreParameters")
public class KeyStoreParametersFactoryBean extends AbstractKeyStoreParametersFactoryBean
        implements FactoryBean<KeyStoreParameters>, ApplicationContextAware {

    @XmlTransient
    private ApplicationContext applicationContext;

    @Override
    protected CamelContext getCamelContextWithId(String camelContextId) {
        return CamelContextResolverHelper.getCamelContextWithId(applicationContext, camelContextId);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
