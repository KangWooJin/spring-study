package kangwoojin.github.io.springsecurity.user;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@TestConstructor(autowireMode = AutowireMode.ALL)
@RequiredArgsConstructor
@Transactional
class AccountControllerTest {
    final MockMvc mockMvc;
    final AccountService accountService;

    @Test
    @WithAnonymousUser
    void indexAnonymous() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk());
    }

    @Test
    @WithUser
    void indexUser() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void indexAdmin() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk());
    }

    @Test
    @WithUser
    void adminUser() throws Exception {
        mockMvc.perform(get("/admin").with(user("admin").roles("USER")))
               .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void adminAdmin() throws Exception {
        mockMvc.perform(get("/admin").with(user("admin").roles("ADMIN")))
               .andExpect(status().isOk());
    }

    @Test
    void loginSuccess() throws Exception {
        String username = "woojin";
        String password = "123";
        Account user = getAccount(username, password);
        mockMvc.perform(formLogin().user(user.getUsername()).password(password))
               .andExpect(authenticated());
    }

    @Test
    void loginFail() throws Exception {
        String username = "woojin";
        String password = "123";
        Account user = getAccount(username, password);
        mockMvc.perform(formLogin().user(user.getUsername()).password("empty"))
               .andExpect(unauthenticated());
    }

    private Account getAccount(String username, String password) {
        Account user = Account.builder()
                              .username(username)
                              .password(password)
                              .role("USER")
                              .build();
        accountService.createNew(user);
        return user;
    }
}
