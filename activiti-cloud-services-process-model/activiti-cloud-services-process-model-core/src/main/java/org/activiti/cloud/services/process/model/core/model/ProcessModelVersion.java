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

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.activiti.cloud.services.process.model.core.audit.AuditableEntity;
import org.activiti.cloud.services.process.model.core.version.VersionEntity;
import org.activiti.cloud.services.process.model.core.version.VersionIdentifier;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Process model entity
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class ProcessModelVersion extends AuditableEntity<String> implements VersionEntity<ProcessModel> {

    @EmbeddedId
    private VersionIdentifier versionIdentifier;

    @JsonIgnore
    @ManyToOne
    private ProcessModel versionedEntity;

    private String name;

    private String contentType;

    @Lob
    @Column
    private String content;

    @Lob
    @Column
    private String extensions;

    public VersionIdentifier getVersionIdentifier() {
        return versionIdentifier;
    }

    @Override
    public void setVersionIdentifier(VersionIdentifier versionIdentifier) {
        this.versionIdentifier = versionIdentifier;
    }

    public ProcessModel getVersionedEntity() {
        return versionedEntity;
    }

    @Override
    public void setVersionedEntity(ProcessModel versionedEntity) {
        this.versionedEntity = versionedEntity;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtensions() {
        return extensions;
    }

    public void setExtensions(String extensions) {
        this.extensions = extensions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    @Override
    public String getVersion() {
        return versionIdentifier.getVersion();
    }
}