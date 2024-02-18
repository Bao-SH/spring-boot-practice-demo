package com.example.integratewithswagger.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


/**
 * This class is inspired from
 * https://github.com/spring-projects/spring-security-samples/blob/5.7.x/servlet/spring-boot/java/jwt/login/src/main/java/example/RestConfig.java
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

//    @Value("${jwt.public.key}")
//    RSAPublicKey publicKey;
//
//    @Value("${jwt.private.key}")
//    RSAPrivateKey privateKey;

    @Order(1)
    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        return http.securityMatcher("/oauth/test", "/swagger-ui", "/swagger-ui/**", "/v3/api-docs/**","/api-docs/**")
            .authorizeHttpRequests(
                authorize -> authorize
                    .requestMatchers("/swagger-ui", "/swagger-ui/**", "/v3/api-docs/**","/api-docs/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            )
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
//            .httpBasic(Customizer.withDefaults())
            .oauth2Client(Customizer.withDefaults())
            .build();
    }

    /**
     * This bean is used to configure the JWT token. Configure the URLs that should not be protected by the JWT token.
     *
     * @param http the HttpSecurity object
     * @return the HttpSecurity object
     * @throws Exception if an error occurs
     */
//    @Bean
//    @Order(2)
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//            .securityMatcher("/api/user", "/api/user/**", "/oauth/**", "/oauth")
//            .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
//            .cors(AbstractHttpConfigurer::disable)
//            .csrf(AbstractHttpConfigurer::disable)
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
//            .exceptionHandling(exceptions -> exceptions
//                .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
//                .accessDeniedHandler(new BearerTokenAccessDeniedHandler()))
//            .build();
//    }

    @Bean
    @Order(3)
    public SecurityFilterChain filterChain3(HttpSecurity http) throws Exception {
        return http
            .securityMatcher("/custom/**")
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .oauth2Client(Customizer.withDefaults())
            .exceptionHandling(exceptions -> exceptions
                .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                .accessDeniedHandler(new BearerTokenAccessDeniedHandler()))
            .build();
    }

    @Bean
    @Order(4)
    public SecurityFilterChain filterChain4(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors(AbstractHttpConfigurer::disable).build();
    }

    /**
     * For demonstration/example, we use the InMemoryUserDetailsManager.
     *
     * @return Returns the UserDetailsService with pre-configure credentials.
     * @see InMemoryUserDetailsManager
     */
    @Bean
    UserDetailsService allUsers() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager
                .createUser(User.builder()
                        .username("john")
                        .password(passwordEncoder().encode("password"))
                        .authorities("USER")
                        .roles("USER").build());
        manager
                .createUser(User.builder()
                        .username("jane")
                        .password(passwordEncoder().encode("password"))
                        .authorities("USER")
                        .roles("USER").build());
        return manager;
    }

    /**
     * This bean is used to decode the JWT token.
     *
     * @return Returns the JwtDecoder bean to decode JWT tokens.
     */
//    @Bean
//    JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
//    }
//
//    /**
//     * This bean is used to encode the JWT token.
//     *
//     * @return Returns the JwtEncoder bean to encode JWT tokens.
//     */
//    @Bean
//    JwtEncoder jwtEncoder() {
//        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
//        return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(jwk)));
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

