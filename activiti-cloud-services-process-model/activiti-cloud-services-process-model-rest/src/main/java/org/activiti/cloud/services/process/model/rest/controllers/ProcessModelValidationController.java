/*
 * Copyright 2018 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.cloud.services.process.model.rest.controllers;

import java.io.IOException;
import java.util.List;
import javax.xml.stream.XMLStreamException;

import org.activiti.cloud.services.process.model.services.validate.ProcessModelValidatorService;
import org.activiti.cloud.services.process.model.services.validate.ValidationErrorRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.activiti.cloud.services.process.model.rest.config.RestRepositoryAutoConfiguration.VERSION_PREFIX;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

@RestController
public class ProcessModelValidationController {

    private final ProcessModelValidatorService validatorService;

    @Autowired
    public ProcessModelValidationController(ProcessModelValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @RequestMapping(value = VERSION_PREFIX + "/process-models/validate",
            method = RequestMethod.POST,
            consumes = APPLICATION_OCTET_STREAM_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public List<ValidationErrorRepresentation> validateBPMNmodel(@RequestBody byte[] file) throws IOException, XMLStreamException {
        return validatorService.validate(file);
    }
}
