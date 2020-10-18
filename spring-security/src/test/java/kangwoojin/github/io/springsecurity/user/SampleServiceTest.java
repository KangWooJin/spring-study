package kangwoojin.github.io.springsecurity.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;

import kangwoojin.github.io.springsecurity.sample.SampleService;

@SpringBootTest
class SampleServiceTest {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private AccountService accountService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Test
    @WithMockUser(roles = "ADMIN")
    void dashBoard() {
//        Account account = new Account();
//        account.setRole("ADMIN");
//        account.setUsername("woojin");
//        account.setPassword("1234");
//        accountService.createNew(account);
//
//        UserDetails userDetails = accountService.loadUserByUsername("woojin");
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, "1234");
//
//        Authentication authenticate = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authenticate);

        sampleService.dashboard();
    }
}
