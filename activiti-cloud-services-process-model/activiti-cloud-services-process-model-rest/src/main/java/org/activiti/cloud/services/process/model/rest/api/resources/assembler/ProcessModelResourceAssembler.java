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
package org.activiti.cloud.services.process.model.rest.api.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.activiti.cloud.services.process.model.core.model.ProcessModel;
import org.activiti.cloud.services.process.model.rest.api.ProcessModelController;
import org.activiti.cloud.services.process.model.rest.api.resources.ProcessModelResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * REST resource assembler for ProcessModelResource
 */
@Component
public class ProcessModelResourceAssembler extends ResourceAssemblerSupport<ProcessModel, ProcessModelResource> {

    public ProcessModelResourceAssembler() {
        super(ProcessModelController.class, ProcessModelResource.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcessModelResource toResource(final ProcessModel processModel) {
        Link selfRel = linkTo(methodOn(ProcessModelController.class).getProcessModel(processModel.getId())).withSelfRel();
        return new ProcessModelResource(processModel, selfRel);
    }

}
