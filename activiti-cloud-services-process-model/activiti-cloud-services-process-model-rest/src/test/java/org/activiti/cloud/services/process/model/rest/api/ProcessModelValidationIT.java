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

package org.activiti.cloud.services.process.model.rest.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import javax.xml.stream.XMLStreamException;

import org.activiti.cloud.services.process.model.config.ProcessModelRestTestApplication;
import org.activiti.cloud.services.process.model.rest.controllers.ProcessModelValidationController;
import org.activiti.cloud.services.process.model.services.validate.ValidationErrorRepresentation;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProcessModelRestTestApplication.class)
public class ProcessModelValidationIT {

    @Autowired
    private ProcessModelValidationController processModelValidationController;

    @Test
    public void validateSimpleProcessIsNotExecutable() throws URISyntaxException, IOException, XMLStreamException {
        MultipartFile multipartFile = new CommonsMultipartFile(loadFile("bpmn/diagram.bpmn"));

        final List<ValidationErrorRepresentation> validationErrors = processModelValidationController.validateBPMNmodel(multipartFile);
        assertThat(validationErrors).isNotEmpty().hasSize(1);
    }

    private FileItem loadFile(String fileToImport) throws URISyntaxException, IOException {
        URL resource = this.getClass().getClassLoader().getResource(fileToImport);
        assertThat(resource).isNotNull();

        final File file = new File(resource.toURI());
        FileItem fileItem = new DiskFileItem("diagram.bpmn",
                                             Files.probeContentType(file.toPath()),
                                             false,
                                             file.getName(),
                                             (int) file.length(),
                                             file.getParentFile());

        InputStream input = new FileInputStream(file);
        OutputStream os = fileItem.getOutputStream();
        IOUtils.copy(input,
                     os);
        return fileItem;
    }
}
