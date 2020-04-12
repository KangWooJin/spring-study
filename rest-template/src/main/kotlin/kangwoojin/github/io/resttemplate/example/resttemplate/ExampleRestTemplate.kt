package kangwoojin.github.io.resttemplate.example.resttemplate

import kangwoojin.github.io.resttemplate.example.ExampleController
import kangwoojin.github.io.resttemplate.example.config.RestTemplateResponseErrorHandler
import kangwoojin.github.io.resttemplate.example.exception.BadRequest
import org.slf4j.LoggerFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import org.springframework.web.client.postForEntity

@Component
class ExampleRestTemplate {
    private val restTemplate: RestTemplate = RestTemplate()
    private val log = LoggerFactory.getLogger(javaClass)
    private var errorHandlerRestTemplate: RestTemplate? = null

    constructor(requestBuilder: RestTemplateBuilder) {
        errorHandlerRestTemplate = requestBuilder.errorHandler(RestTemplateResponseErrorHandler()).build()
    }

    fun successExample(): String {
        val result = restTemplate.getForEntity<String>("http://localhost:8080/example")
        print(result)

        return result.body.toString()
    }

    @Throws(BadRequest::class)
    fun failExample(): String? {
        var result: ResponseEntity<String>? = null
        try {
            result = restTemplate.getForEntity<String>("http://localhost:8080/badRequest")
        } catch (e: HttpClientErrorException) {
            log.error("exception {}", e.message, e)
            throw BadRequest(e)
        }
        print(result)

        return result.body?.toString()
    }

    fun failExampleByErrorHandler(): ExampleController.ExampleResponse? {
        val result = errorHandlerRestTemplate?.postForEntity<ExampleController.ExampleResponse>("http://localhost:8080/badRequest")

        printResponse(result)

        return result?.body
    }


    private fun <T> printResponse(result: ResponseEntity<T>?) {
        if (result == null) {
            return
        }
        log.info("result status code {}", result.statusCode)
        log.info("result body {}", result.body)
        log.info("result headers {}", result.headers)
        log.info("result statusCodeValue {}", result.statusCodeValue)
    }
}