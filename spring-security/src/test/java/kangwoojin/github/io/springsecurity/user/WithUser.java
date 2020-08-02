package kangwoojin.github.io.springsecurity.user;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithMockUser;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "woojin", roles = "USER", password = "123")
public @interface WithUser {
}
