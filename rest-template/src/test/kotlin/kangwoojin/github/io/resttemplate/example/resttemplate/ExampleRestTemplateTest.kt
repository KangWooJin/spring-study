package kangwoojin.github.io.resttemplate.example.resttemplate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ExampleRestTemplateTest(@Autowired private val exampleRestTemplate: ExampleRestTemplate) {

    @Test
    internal fun exampleTest() {
        val result = exampleRestTemplate.getExample();

        assertThat(result).isEqualTo("success")
    }
}