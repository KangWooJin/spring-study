package kangwoojin.github.io.openfeign.feign

import org.springframework.cloud.openfeign.FeignClient

@FeignClient(name = "example", url = "\${feign.example-api.url}",
        configuration = [
            ExampleConfiguration::class,
            RetryConfiguration::class
        ])
interface ExampleClient : AbstractClient {

}