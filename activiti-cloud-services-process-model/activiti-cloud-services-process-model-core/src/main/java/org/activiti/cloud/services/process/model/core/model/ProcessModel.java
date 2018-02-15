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
package org.activiti.cloud.services.process.model.core.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.activiti.cloud.services.process.model.core.version.VersionedEntity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Process model entity
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
@Entity
public class ProcessModel implements VersionedEntity<ProcessModelVersion> {

    @Id
    private String modelId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProcessModelVersion> versions = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private ProcessModelVersion latestVersion;

    @Transient
    private String name;

    @Transient
    private String content;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonIgnore
    public void setVersions(List<ProcessModelVersion> versions) {
        this.versions = versions;
    }

    @Override
    public List<ProcessModelVersion> getVersions() {
        return versions;
    }

    @Override
    @JsonIgnore
    public void setLatestVersion(ProcessModelVersion latestVersion) {
        this.latestVersion = latestVersion;
    }

    @Override
    public ProcessModelVersion getLatestVersion() {
        return latestVersion;
    }

    @Transient
    @JsonUnwrapped
    public ProcessModelVersion getLatestVersionUnwrapped() {
        return latestVersion;
    }

    @Override
    @JsonIgnore
    public String getId() {
        return modelId;
    }

    @Override
    public void fillVersionContent(ProcessModelVersion processModelVersion) {
        processModelVersion.setName(getName());
        processModelVersion.setContent(getContent());
    }
}