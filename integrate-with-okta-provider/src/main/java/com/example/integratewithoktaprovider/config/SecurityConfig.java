package com.example.integratewithoktaprovider.config;

import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
@Data
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, SecurityProperties securityProperties) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(securityProperties.getEndpoints().toArray(new String[0])).authenticated()
                .anyRequest().permitAll())
            .cors(withDefaults())
            .oauth2ResourceServer(oauth2ResourceServer ->
                oauth2ResourceServer
                    .jwt(withDefaults()));

        return http.build();
    }
}
