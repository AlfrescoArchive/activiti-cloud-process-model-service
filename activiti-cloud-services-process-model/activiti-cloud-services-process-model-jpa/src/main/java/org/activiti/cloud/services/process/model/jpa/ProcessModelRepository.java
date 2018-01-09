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
package org.activiti.cloud.services.process.model.jpa;

import org.activiti.cloud.services.process.model.core.model.ProcessModel;
import org.activiti.cloud.services.process.model.core.model.ProcessModelVersion;
import org.activiti.cloud.services.process.model.jpa.version.VersionedJpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * JPA Repository for {link @ProcessModel} entity
 */
@RepositoryRestResource(path = "process-models",
                        collectionResourceRel = "process-models",
                        itemResourceRel = "process-models")
public interface ProcessModelRepository extends VersionedJpaRepository<ProcessModel, String, ProcessModelVersion> {
}