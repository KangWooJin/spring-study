package kangwoojin.github.io.openfeign.feign

import feign.Logger
import org.springframework.cloud.openfeign.FeignFormatterRegistrar
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar


@Configuration
class ExampleConfiguration {
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
}