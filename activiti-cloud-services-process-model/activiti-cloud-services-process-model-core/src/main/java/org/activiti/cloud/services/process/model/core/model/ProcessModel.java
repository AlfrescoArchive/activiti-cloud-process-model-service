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

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Process model entity
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class ProcessModel {

    @Id
    private String id;

    private String name;

//    private String[] labels;
//
//    private Version[] versions;

    public ProcessModel(String id,
                        String name/*,
                        String[] labels,
                        Version[] versions*/) {
        this.id = id;
        this.name = name;
        //this.labels = labels;
        //this.versions = versions;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public String[] getLabels() {
//        return labels;
//    }
//
//    public Version[] getVersion() {
//        return versions;
//    }
}