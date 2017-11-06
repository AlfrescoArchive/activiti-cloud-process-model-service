import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a scenario when a process model is created.
```
given:
    any client
when:
    he post a process model
then:
    the process model is created
```
""")
    request {
        method 'POST'
        url '/v1/process-models'
        headers {
            header('Content-Type': 'application/json')
        }
        body(
            id: "newProcesModelId",
            name: "newProcesModelName"
        )
    }
    response {
        status 201
    }
}
