package kangwoojin.github.aspect.controller

import kangwoojin.github.aspect.service.ExampleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController(@Autowired private val exampleService: ExampleService) {


    @GetMapping("/around")
    fun logging(@RequestParam(defaultValue = "false") flag: Boolean): Map<String, String> {

        return mapOf("key" to exampleService.serve(flag))
    }
}