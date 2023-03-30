package Harvest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           AuthenticationConfiguration config) throws Exception {
        http.csrf().disable();
        http.cors();



       http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/create").permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
