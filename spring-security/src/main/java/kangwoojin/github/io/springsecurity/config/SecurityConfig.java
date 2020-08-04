package kangwoojin.github.io.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity(debug = true)
@Order(Ordered.LOWEST_PRECEDENCE - 20)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static String data;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .mvcMatchers("/", "/info", "/account/**").permitAll()
            .mvcMatchers("/admin").hasAnyRole("ADMIN")
            .mvcMatchers("/user").hasAnyRole("USER")
            .anyRequest().authenticated();

        http.formLogin();
        http.httpBasic();
    }
}
