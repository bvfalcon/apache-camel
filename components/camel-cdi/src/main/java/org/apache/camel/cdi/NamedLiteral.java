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
package org.apache.camel.cdi;

import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Named;

@Vetoed
final class NamedLiteral extends AnnotationLiteral<Named> implements Named {

    private static final long serialVersionUID = 1L;

    private final String name;

    private NamedLiteral(String name) {
        this.name = name;
    }

    public static Named of(String name) {
        return new NamedLiteral(name);
    }

    @Override
    public String value() {
        return name;
    }
}
