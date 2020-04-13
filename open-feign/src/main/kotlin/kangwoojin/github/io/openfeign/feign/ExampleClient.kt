package kangwoojin.github.io.openfeign.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.cloud.openfeign.SpringQueryMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "example", url = "http://localhost:8080")
interface ExampleClient {

    @GetMapping("/success")
    fun success(): String

    @GetMapping("/success/{id}")
    fun successById(@PathVariable(name = "id") id: Long): String

    @GetMapping("/success/queryMap")
    fun successQueryMap(@SpringQueryMap params: Params): String
}