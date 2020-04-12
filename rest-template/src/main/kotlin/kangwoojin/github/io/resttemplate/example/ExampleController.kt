package kangwoojin.github.io.resttemplate.example

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController {

    @GetMapping("/example")
    fun example(): String {
        return "success"
    }
}