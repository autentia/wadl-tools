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

import com.autentia.web.rest.wadl.builder.namespace.GrammarsDiscoverer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class ParamFromAnnotationBuilderFactory {

    private final Map<String, ParamFromAnnotationBuilder> paramBuilderByAnnotation = new HashMap<String, ParamFromAnnotationBuilder>();

    public ParamFromAnnotationBuilderFactory(GrammarsDiscoverer grammarsDiscoverer) {
        paramBuilderByAnnotation.put(RequestParam.class.getName(), new ParamFromRequestParamBuilder(grammarsDiscoverer));
        paramBuilderByAnnotation.put(PathVariable.class.getName(), new ParamFromPathVariableBuilder(grammarsDiscoverer));
        paramBuilderByAnnotation.put(RequestBody.class.getName(), new ParamFromRequestBodyBuilder(grammarsDiscoverer));
    }

    public ParamFromAnnotationBuilder builderFor(Annotation annotation) {
        return paramBuilderByAnnotation.get(annotation.annotationType().getName());
    }
}
