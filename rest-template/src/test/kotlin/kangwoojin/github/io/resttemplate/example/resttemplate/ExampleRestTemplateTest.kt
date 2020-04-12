package kangwoojin.github.io.resttemplate.example.resttemplate

import kangwoojin.github.io.resttemplate.example.exception.BadRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ExampleRestTemplateTest(@Autowired private val exampleRestTemplate: ExampleRestTemplate) {
    private val log = LoggerFactory.getLogger(javaClass)


    @Test
    internal fun exampleTest() {
        val result = exampleRestTemplate.successExample();

        assertThat(result).isEqualTo("success")
    }

    @Test
    internal fun badRequestTest() {
        assertThrows<BadRequest> {
            exampleRestTemplate.failExample()
        }
    }

    @Test
    internal fun errorHandlerTest() {
        val actual = exampleRestTemplate.failExampleByErrorHandler()
        assertThat(actual).isNotNull()
    }
}