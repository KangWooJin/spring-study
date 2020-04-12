package kangwoojin.github.io.resttemplate.example

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class ExampleController {

    @GetMapping("/example")
    fun example(): String {
        return "success"
    }

    @PostMapping("/badRequest")
    fun badRequest(): ResponseEntity<ExampleResponse> {
        val response = ExampleResponse("message", "1", UUID.randomUUID())
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    class ExampleResponse(var message: String?, var version: String?, var uuid: UUID?)
}