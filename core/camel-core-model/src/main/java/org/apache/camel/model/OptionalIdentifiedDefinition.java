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
package org.apache.camel.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.NamedNode;
import org.apache.camel.spi.IdAware;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.NodeIdFactory;

/**
 * Allows an element to have an optional ID specified
 */
@XmlType(name = "optionalIdentifiedDefinition")
@XmlAccessorType(XmlAccessType.PROPERTY)
// must use XmlAccessType.PROPERTY which is required by camel-spring / camel-blueprint for their namespace parsers
public abstract class OptionalIdentifiedDefinition<T extends OptionalIdentifiedDefinition<T>>
        implements NamedNode, IdAware, CamelContextAware {

    private CamelContext camelContext;
    private String id;
    private Boolean customId;
    private DescriptionDefinition description;
    private transient int lineNumber = -1;
    private transient String location;

    @Override
    public CamelContext getCamelContext() {
        return camelContext;
    }

    @Override
    @XmlTransient
    public void setCamelContext(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * Sets the id of this node
     */
    @XmlAttribute
    @Metadata(description = "The id of this node")
    public void setId(String id) {
        this.id = id;
        customId = true;
    }

    @Override
    public void setGeneratedId(String id) {
        this.id = id;
        customId = null;
    }

    public DescriptionDefinition getDescription() {
        return description;
    }

    /**
     * Sets the description of this node
     *
     * @param description sets the text description, use null to not set a text
     */
    @XmlElement
    @Metadata(description = "The description for this node")
    public void setDescription(DescriptionDefinition description) {
        this.description = description;
    }

    @Override
    public NamedNode getParent() {
        return null;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    @XmlTransient
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    @XmlTransient
    public void setLocation(String location) {
        this.location = location;
    }

    // Fluent API
    // -------------------------------------------------------------------------

    /**
     * Sets the description of this node
     *
     * @param  text sets the text description, use null to not set a text
     * @return      the builder
     */
    @SuppressWarnings("unchecked")
    public T description(String text) {
        if (text != null) {
            if (description == null) {
                description = new DescriptionDefinition();
            }
            description.setText(text);
        }
        return (T) this;
    }

    /**
     * Sets the description of this node
     *
     * @param  id   sets the id, use null to not set an id
     * @param  text sets the text description, use null to not set a text
     * @param  lang sets the language for the description, use null to not set a language
     * @return      the builder
     */
    @SuppressWarnings("unchecked")
    @Deprecated
    public T description(String id, String text, String lang) {
        if (id != null) {
            setId(id);
        }
        if (text != null) {
            if (description == null) {
                description = new DescriptionDefinition();
            }
            description.setText(text);
        }
        if (lang != null) {
            if (description == null) {
                description = new DescriptionDefinition();
            }
            description.setLang(lang);
        }
        return (T) this;
    }

    /**
     * Sets the id of this node.
     * <p/>
     * <b>Important:</b> If you want to set the id of the route, then you <b>must</b> use <tt>routeId(String)</tt>
     * instead.
     *
     * @param  id the id
     * @return    the builder
     */
    @SuppressWarnings("unchecked")
    public T id(String id) {
        setId(id);
        return (T) this;
    }

    /**
     * Gets the node id, creating one if not already set.
     */
    public String idOrCreate(NodeIdFactory factory) {
        if (id == null) {
            setGeneratedId(factory.createId(this));
        }
        return id;
    }

    public Boolean getCustomId() {
        return customId;
    }

    /**
     * Whether the node id was explicit set, or was auto generated by Camel.
     */
    @XmlAttribute
    public void setCustomId(Boolean customId) {
        this.customId = customId;
    }

    /**
     * Returns whether a custom id has been assigned
     */
    public boolean hasCustomIdAssigned() {
        return customId != null && customId;
    }

    /**
     * Returns the description text or null if there is no description text associated with this node
     */
    @Override
    public String getDescriptionText() {
        return (description != null) ? description.getText() : null;
    }

    // Implementation methods
    // -------------------------------------------------------------------------

}
