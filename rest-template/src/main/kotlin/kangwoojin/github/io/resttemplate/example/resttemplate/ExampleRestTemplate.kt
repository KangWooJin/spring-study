package kangwoojin.github.io.resttemplate.example.resttemplate

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@Component
class ExampleRestTemplate {
    private val restTemplate: RestTemplate = RestTemplate()
    private val log = LoggerFactory.getLogger(javaClass)

    fun getExample(): String {
        val result = restTemplate.getForEntity<String>("http://localhost:8080/example")
        print(result)

        return result.body.toString()
    }

    private fun print(result: ResponseEntity<String>) {
        log.info("result status code{}", result.statusCode)
        log.info("result body {}", result.body)
        log.info("result headers{}", result.headers)
        log.info("result statusCodeValue {}", result.statusCodeValue)
    }
}