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

package org.activiti.cloud.process.model.rest;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.activiti.cloud.process.model.ProcessModelApplication;
import org.activiti.cloud.services.process.model.core.model.ProcessModel;
import org.activiti.cloud.services.process.model.jpa.ProcessModelRepository;
import org.activiti.cloud.services.test.identity.keycloak.interceptor.KeycloakTokenProducer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProcessModelApplication.class)
public class ProcessModelVersionRestIT {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ProcessModelRepository processModelRepository;

    @Before
    public void setUp() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    @Test
    public void testCreateProcessModel() throws Exception {
        given()
                .body("{\"modelId\":\"11\",\"name\":\"Process 11\"}")
                .post("/v1/process-models")
                .then().expect(status().isCreated());

        given()
                .get("/v1/process-models/11")
                .then().expect(status().isOk())
                .and().body("name",
                            equalTo("Process 11"));
    }

    @Test
    public void testUpdateProcessModel() throws Exception {
        ProcessModel processModel = new ProcessModel();
        processModel.setModelId("22");
        processModel.setName("Process 22");
        processModel.setContent("ContentVersion001");
        processModelRepository.save(processModel);

        given()
                .body("{\"name\":\"Process 22_2\",\"content\":\"ContentVersion002\"}")
                .put("/v1/process-models/22")
                .then().expect(status().isNoContent());

        given()
                .get("/v1/process-models/22")
                .then().expect(status().isOk())
                .and().body("name",
                            equalTo("Process 22_2"))
                .and().body("content",
                            equalTo("ContentVersion002"))
                .and().body("version",
                            equalTo("0.0.2"));

        given()
                .get("/v1/process-models/22/versions")
                .then().expect(status().isOk())
                .and().body("_embedded.versions.size()",
                            equalTo(2));

        given()
                .get("/v1/process-models/22/versions/0.0.1/")
                .then().expect(status().isOk())
                .and().body("name",
                            equalTo("Process 22"))
                .and().body("content",
                            equalTo("ContentVersion001"))
                .and().body("version",
                            equalTo("0.0.1"));

        given()
                .get("/v1/process-models/22/versions/0.0.2/")
                .then().expect(status().isOk())
                .and().body("name",
                            equalTo("Process 22_2"))
                .and().body("content",
                            equalTo("ContentVersion002"))
                .and().body("version",
                            equalTo("0.0.2"));
    }
}
