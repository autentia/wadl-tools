/**
 *    Copyright 2013 Autentia Real Business Solution S.L.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.autentia.web.rest.wadl.builder.param;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ValueConstants;

import com.autentia.lang.ClassMetadataFromParam;
import com.autentia.web.rest.wadl.builder.namespace.GrammarsDiscoverer;

import net.java.dev.wadl._2009._02.Param;
import net.java.dev.wadl._2009._02.ParamStyle;

class ParamFromRequestBodyBuilder extends ParamFromAnnotationBuilderCommons {

    ParamFromRequestBodyBuilder(GrammarsDiscoverer grammarsDiscoverer) {
        super(grammarsDiscoverer);
    }

    @Override
    public Param build(Method javaMethod, int paramIndex, Annotation paramAnnotation) {
        final RequestBody requestParam = (RequestBody) paramAnnotation;
        final Param param = new Param()
                .withName(discoverParamName(javaMethod, paramIndex, 
                		javaMethod.getParameterTypes()[paramIndex].getCanonicalName()
                		))
                .withStyle(ParamStyle.QUERY)
                .withRequired(requestParam.required())
                .withType(grammarsDiscoverer.discoverQNameFor(new ClassMetadataFromParam(javaMethod, paramIndex)));

        return param;
    }
}
