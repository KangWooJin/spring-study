package kangwoojin.github.io.springsecurity.user;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithMockUser;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "admin", roles = "USER")
public @interface WithUser {
}
