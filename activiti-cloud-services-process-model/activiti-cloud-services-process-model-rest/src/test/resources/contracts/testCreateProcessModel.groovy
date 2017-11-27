import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a scenario when a process model is created.
```
given:
    any client
when:
    post a process model
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
            modelId: anyNonEmptyString(),
            name: anyNonEmptyString()
        )
    }
    response {
        status 201
    }
}
