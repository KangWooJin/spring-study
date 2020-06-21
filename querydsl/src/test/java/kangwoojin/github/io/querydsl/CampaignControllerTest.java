package kangwoojin.github.io.querydsl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@AutoConfigureMockMvc
@SpringBootTest
public class CampaignControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/create")
                                                      .param("name", "event")
                                                      .contentType(MediaType.APPLICATION_JSON))
                                     .andReturn();
    }
}
