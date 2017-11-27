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
Represents a scenario to update a process model.
```
given:
    any client
when:
    updates a process model by process model id
then:
    the corresponding process model is updated
```
""")
    request {
        method 'PUT'
        url "/v1/process-models/contractUpdateProcesModelId"
        headers { 
            header('Content-Type': 'application/json')
        }
        body(
            name: anyNonEmptyString(),
            content: anyNonEmptyString()
        )
    }
    response {
        status 204
    }
}
