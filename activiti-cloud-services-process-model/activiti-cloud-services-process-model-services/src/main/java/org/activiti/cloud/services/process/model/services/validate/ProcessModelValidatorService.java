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

package org.activiti.cloud.services.process.model.services.validate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.validation.ProcessValidatorImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import static org.activiti.bpmn.converter.util.BpmnXMLUtil.createSafeXmlInputFactory;

@Component
@PreAuthorize("hasRole('ACTIVITI_MODELER')")
public class ProcessModelValidatorService {

    private final ProcessValidatorImpl processValidator;
    private final BpmnXMLConverter bpmnXmlConverter;

    public ProcessModelValidatorService(ProcessValidatorImpl processValidator,
                                        BpmnXMLConverter bpmnXmlConverter) {
        this.processValidator = processValidator;
        this.bpmnXmlConverter = bpmnXmlConverter;
    }

    public List<ValidationErrorRepresentation> validate(byte[] file) throws XMLStreamException, IOException {

        try (InputStreamReader xmlIn = new InputStreamReader(new ByteArrayInputStream(file),
                                                             "UTF-8")) {
            XMLInputFactory xif = createSafeXmlInputFactory();
            XMLStreamReader xtr = xif.createXMLStreamReader(xmlIn);
            BpmnModel bpmnModel = bpmnXmlConverter.convertToBpmnModel(xtr);

            if (CollectionUtils.isEmpty(bpmnModel.getProcesses())) {
                throw new IllegalStateException("No process found for validation.");
            }

            return processValidator
                    .validate(bpmnModel)
                    .stream()
                    .map(ValidationErrorRepresentation::new)
                    .collect(Collectors.toList());
        }
    }
}
