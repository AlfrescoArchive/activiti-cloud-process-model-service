/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.activiti.cloud.services.process.model.rest.impl;

import org.activiti.cloud.services.process.model.jpa.ProcessModelRepository;
import org.activiti.cloud.services.process.model.rest.api.ProcessModelController;
import org.activiti.cloud.services.process.model.rest.api.resources.ProcessModelResource;
import org.activiti.cloud.services.process.model.rest.api.resources.assembler.ProcessModelResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation controller for {link @ProcessModelController}
 */
@RestController
public class ProcessModelControllerImpl implements ProcessModelController {

    private final ProcessModelResourceAssembler resourceAssembler;
    
    private final ProcessModelRepository processModelRepository;

    /**
     * @param resourceAssembler the ProcessModelResourceAssembler bean
     * @param processModelRepository the ProcessModelRepository bean
     */
    @Autowired
    public ProcessModelControllerImpl(final ProcessModelResourceAssembler resourceAssembler,
                                      final ProcessModelRepository processModelRepository) {
        this.resourceAssembler = resourceAssembler;
        this.processModelRepository = processModelRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcessModelResource getProcessModel(String id) {
        return processModelRepository.findById(id)
            .map(processModel -> {
                return resourceAssembler.toResource(processModel);
             })
            .orElseThrow(() -> new ResourceNotFoundException());
    }

}
