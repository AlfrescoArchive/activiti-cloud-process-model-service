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

import org.activiti.cloud.services.process.model.rest.assemblers.ValidationErrorResourceAssembler;
import org.activiti.cloud.services.process.model.rest.config.ActivitiRepositoryRestConfiguration;
import org.activiti.cloud.services.process.model.rest.resources.ValidationErrorResource;
import org.activiti.cloud.services.process.model.services.validate.ProcessModelValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProcessModelValidationController {

    private final ProcessModelValidatorService validatorService;

    private final ValidationErrorResourceAssembler validationErrorResourceAssembler;

    @Autowired

    public ProcessModelValidationController(ProcessModelValidatorService validatorService,
                                            ValidationErrorResourceAssembler validationErrorResourceAssembler) {
        this.validatorService = validatorService;
        this.validationErrorResourceAssembler = validationErrorResourceAssembler;
    }

    @RequestMapping(value = ActivitiRepositoryRestConfiguration.VERSION_PREFIX + "/models/validate", method = RequestMethod.POST, produces = "application/json")
    public List<ValidationErrorResource> validateBPMNmodel(@RequestParam("file") MultipartFile file) throws IOException, XMLStreamException {
        return validationErrorResourceAssembler.toResources(validatorService.validate(file));
    }
}
