package kangwoojin.github.io.openfeign

import feign.RetryableException
import kangwoojin.github.io.openfeign.feign.ExampleClient
import kangwoojin.github.io.openfeign.feign.Params
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OpenFeignApplicationTests(@Autowired private val exampleClient: ExampleClient) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun successTest() {
        val success = exampleClient.success()

        assertThat(success).isEqualTo("success")
    }

    @Test
    fun successByIdTest() {
        val success = exampleClient.successById(1234L)

        assertThat(success).isEqualTo("1234")
    }

    @Test
    fun successQueryMapTest() {

        val params = Params("test1", "test2")
        val success = exampleClient.successQueryMap(params)

        assertThat(success).isEqualTo("test1 + test2")
    }

    @Test
    fun errorRetryTest() {
        assertThrows<RetryableException> {
            val error = exampleClient.error(500)

            assertThat(error).isNotEmpty()
        }
    }

    @Test
    fun runtimeExceptionTest() {
        try {
            val error = exampleClient.error(400)
        } catch (ex: Exception) {
            println(ex)
        }
    }

    @Test
    fun responseTest() {
        val response = exampleClient.response(500)

        log.info("response {}", response)
    }
}
