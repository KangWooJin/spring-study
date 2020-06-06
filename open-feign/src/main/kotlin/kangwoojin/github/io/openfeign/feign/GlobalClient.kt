package kangwoojin.github.io.openfeign.feign

import feign.CollectionFormat
import feign.Param
import feign.RequestLine
import org.springframework.cloud.openfeign.FeignClient

@FeignClient(name = "global", url = "\${feign.example-api.url}")
interface GlobalClient {

    @RequestLine(value = "GET /list?ids={ids}", collectionFormat = CollectionFormat.CSV)
    fun listByRequestLine(@Param(value = "ids") ids: List<Int>): List<Int>
}