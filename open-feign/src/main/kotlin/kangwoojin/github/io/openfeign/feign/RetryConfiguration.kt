package kangwoojin.github.io.openfeign.feign

import feign.Request
import feign.RetryableException
import feign.Retryer
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import java.util.*


class RetryConfiguration {
    @Bean
    fun retryer(): Retryer {
        return Retryer.Default(1000, 2000, 3)
    }

    @Bean
    fun decoder(): ErrorDecoder {
        return ErrorDecoder { methodKey, response ->
            if (HttpStatus.valueOf(response.status()).is5xxServerError) {
                throw RetryableException(response.status(), response.reason(), Request.HttpMethod.GET, Date(), response.request())
            }
            throw RuntimeException()
        }
    }
}