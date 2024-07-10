package alexburtyn.com.trainss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for development purposes
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll() // Allow all requests without authentication
                        .anyRequest().authenticated()); // Require authentication for any other request

        return http.build();
    }
}

