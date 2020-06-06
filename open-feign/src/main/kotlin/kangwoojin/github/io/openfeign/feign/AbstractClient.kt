package kangwoojin.github.io.openfeign.feign

import feign.Param
import feign.Response
import org.springframework.cloud.openfeign.SpringQueryMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

interface AbstractClient {

    @GetMapping("/success")
    fun success(): String

    @GetMapping("/success/{id}")
    fun successById(@PathVariable(name = "id") id: Long): String

    @GetMapping("/success/queryMap")
    fun successQueryMap(@SpringQueryMap params: Params): String

    @GetMapping("/error/{code}")
    fun error(@PathVariable(name = "code") code: Int): String

    @GetMapping("/response/{code}")
    fun response(@PathVariable(name = "code") code: Int): Response

    @GetMapping("/list/{ids}")
    fun list(@PathVariable ids: List<Int>): List<Int>

//    @GetMapping("/list")
//    fun listByRequestParam(@RequestParam(value = "ids") ids: List<Int>): List<Int>

    @GetMapping("/list")
//    @RequestLine(value = "GET /list?ids={ids}", collectionFormat = CollectionFormat.CSV)
    fun listByRequestLine(@Param(value = "ids") ids: List<Int>): List<Int>
}