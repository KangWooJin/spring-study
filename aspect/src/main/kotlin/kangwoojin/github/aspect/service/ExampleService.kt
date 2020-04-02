package kangwoojin.github.aspect.service

import kangwoojin.github.aspect.aspect.LogExecutionTime
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ExampleService {
    private val log = LoggerFactory.getLogger(javaClass)

    @LogExecutionTime
    fun serve(flag: Boolean): String {
        log.info("serve")
        if (flag) {
            throw RuntimeException()
        }
        return "serve";
    }
}
