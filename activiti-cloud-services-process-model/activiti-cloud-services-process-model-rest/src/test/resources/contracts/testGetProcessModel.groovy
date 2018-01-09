import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a scenario to get a process model.
```
given:
    any client
when:
    requests a just created process model by model id
then:
    send back the corresponding process model with version 0.0.1
```
""")
    request {
        method 'GET'
        url "/v1/process-models/contractNewProcesModelId"
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
            name: "contractNewProcesModelName",
            version: "0.0.1"
        )
    }
}
