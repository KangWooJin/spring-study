package kangwoojin.github.io.querydsl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@AutoConfigureMockMvc
@SpringBootTest
public class CampaignControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Disabled
    void createTest() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(post("/create")
//                                                      .param("name", null)
//                                                      .contentType(MediaType.APPLICATION_JSON))
//                                     .andReturn();

        List<String> test2 = new ArrayList<>();
        test2.add(null);
        test2.add(null);
        test2.add(null);
        test2.add("null");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.addAll("name", test2);
        mockMvc.perform(post("/create2")
                                .params(params)
                                .contentType(MediaType.APPLICATION_JSON))
               .andReturn();
    }
}
