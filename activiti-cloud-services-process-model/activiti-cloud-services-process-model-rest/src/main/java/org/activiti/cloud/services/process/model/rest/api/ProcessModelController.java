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
package org.activiti.cloud.services.process.model.rest.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.cloud.services.process.model.rest.api.resources.ProcessModelResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Interface for process model REST controller
 */
@Api("REST API for managing process model resources.")
@RequestMapping(value = ProcessModelController.PROCESS_MODELS_BASE_PATH,
                produces = HAL_JSON_VALUE)
public interface ProcessModelController {
    
    static final String PROCESS_MODELS_BASE_PATH = "/v1/process-models";

    static final String GET_PROCESS_MODEL_REQUEST_MAPPING = "/{id}";

    static final String PROCESS_MODEL_ID_PARAM_NAME = "id";

    /**
     * Get a process model resource for a given id
     * 
     * @param id the process model id to retrieve
     * @return the resource corresponding to given id
     */
    @ApiOperation("REST service to get a process model resource along with all its versions.")
    @RequestMapping(method = GET, value = GET_PROCESS_MODEL_REQUEST_MAPPING)
    ProcessModelResource getProcessModel(
        @ApiParam(required = true, 
                  name = PROCESS_MODEL_ID_PARAM_NAME, 
                  value = "The id of the requested process model") 
        @PathVariable(PROCESS_MODEL_ID_PARAM_NAME) 
        String id);
}
