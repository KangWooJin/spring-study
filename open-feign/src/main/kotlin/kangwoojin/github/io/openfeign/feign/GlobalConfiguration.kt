package kangwoojin.github.io.openfeign.feign

import feign.Logger
import feign.Request
import feign.RetryableException
import feign.Retryer
import feign.codec.ErrorDecoder
import org.springframework.cloud.openfeign.FeignFormatterRegistrar
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar
import org.springframework.http.HttpStatus
import java.util.*


@Configuration
class GlobalConfiguration {
    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

    @Bean
    fun localDateFeignFormatterRegister(): FeignFormatterRegistrar? {
        return FeignFormatterRegistrar { registry: FormatterRegistry ->
            val registrar = DateTimeFormatterRegistrar()
            registrar.setUseIsoFormat(true)
            registrar.registerFormatters(registry)
        }
    }

    @Bean
    fun retryer(): Retryer {
        return Retryer.Default(3000, 10000, 30)
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