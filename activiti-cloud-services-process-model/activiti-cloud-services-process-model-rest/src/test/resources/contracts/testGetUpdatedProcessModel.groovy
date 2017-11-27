/*
 * Copyright 2017 Alfresco, Inc. and/or its affiliates.
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

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a scenario to get a previous (only once) updated process model.
```
given:
    any client
when:
    requests a process model by model id
then:
    the sent back process model has the version 0.0.2
```
""")
    request {
        method 'GET'
        url "/v1/process-models/contractUpdateProcesModelId"
        headers {
            header('Content-Type': 'application/json')
        }
    }
    response {
        status 200
        headers {
            header('Content-Type': 'application/hal+json;charset=UTF-8')
        }
        body(
            name: "contractUpdateProcesModelNameUpdated",
            content: "contractContentVersion002",
            version: "0.0.2"
        )
    }
}
