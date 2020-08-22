package kangwoojin.github.io.springsecurity.sample;

import java.util.Collection;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SampleService {

    public void dashboard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Object credentials = authentication.getCredentials();
        boolean authenticated = authentication.isAuthenticated();

        log.debug("authentication {}", authentication);
        log.debug("principal {}", principal);
        log.debug("authorities {}", authorities);
        log.debug("credentials {}", credentials);
        log.debug("authenticated {}", authenticated);
    }

    @Async
    public void asyncMethod() {
        SampleController.print("asyncMethod");

    }
}
