package kangwoojin.github.io.springsecurity.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE - 20)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
           .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
           .antMatchers("/info")
           .mvcMatchers();

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
            .mvcMatchers("/", "/info", "/account/**").permitAll()
            .mvcMatchers("/admin").hasAnyRole("ADMIN")
            .mvcMatchers("/user").hasAnyRole("USER")
            .anyRequest().authenticated();

        http.formLogin();
        http.httpBasic();
        http.csrf().disable();
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
    }
}
