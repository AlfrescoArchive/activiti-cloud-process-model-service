import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a scenario to get a process model.
```
given:
    any client
when:
    he requests a process model by id
then:
    we'll send him back the corresponding process model
```
""")
    request {
        method 'GET'
        url "/v1/process-models/testProcesModelId"
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
            name: "testProcesModelName"
        )
    }
}
