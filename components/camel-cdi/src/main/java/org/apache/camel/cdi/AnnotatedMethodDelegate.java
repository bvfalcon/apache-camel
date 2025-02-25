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

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import jakarta.enterprise.inject.Vetoed;
import jakarta.enterprise.inject.spi.AnnotatedMethod;
import jakarta.enterprise.inject.spi.AnnotatedParameter;

@Vetoed
final class AnnotatedMethodDelegate<T> extends AnnotatedMemberDelegate<T> implements AnnotatedMethod<T> {

    private final AnnotatedMethod<T> delegate;

    AnnotatedMethodDelegate(AnnotatedMethod<T> delegate, Set<Annotation> annotations) {
        super(delegate, annotations);
        this.delegate = delegate;
    }

    @Override
    public List<AnnotatedParameter<T>> getParameters() {
        return delegate.getParameters();
    }

    @Override
    public Method getJavaMember() {
        return delegate.getJavaMember();
    }
}
