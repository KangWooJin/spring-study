package kangwoojin.github.io.openfeign.feign

import feign.Logger
import org.springframework.context.annotation.Bean


class ExampleConfiguration {
    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.BASIC
    }
}