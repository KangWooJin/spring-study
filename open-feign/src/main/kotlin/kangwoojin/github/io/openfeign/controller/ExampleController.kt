package kangwoojin.github.io.openfeign.controller

import kangwoojin.github.io.openfeign.feign.Params
import org.springframework.cloud.openfeign.SpringQueryMap
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController {

    @GetMapping("/success")
    fun success(): String {
        return "success"
    }

    @GetMapping("/success/{id}")
    fun successById(@PathVariable id: Long): String {
        return id.toString()
    }

    @GetMapping("/success/queryMap")
    fun successQueryMap(@SpringQueryMap params: Params): String {
        return "${params.param1} + ${params.param2}";
    }

    @GetMapping("/error/{code}")
    fun error(@PathVariable code: Int): ResponseEntity<Int> {
        return ResponseEntity.status(code).body(code)
    }
}

