package kangwoojin.github.aspect.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.juli.logging.LogFactory
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@AutoConfigureMockMvc(printOnlyOnFailure = false)
@SpringBootTest
internal class ExampleControllerTest(@Autowired private val mockMvc: MockMvc) {
    val log = LogFactory.getLog(javaClass)
    val objectMapper = jacksonObjectMapper()

    @Test
    internal fun aspectTest() {
        val result = mockMvc.get("/around")
                .andDo { log() }
                .andExpect { status { is2xxSuccessful } }
                .andReturn()

        val readValue = objectMapper.readValue<Map<String, String>>(result.response.contentAsString)


        log.info("result $readValue")
    }
}