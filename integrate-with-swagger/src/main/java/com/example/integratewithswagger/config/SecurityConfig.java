package com.example.integratewithswagger.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(SpringdocConfigProperties.class)
public class SecurityConfig {
    private static final String BASIC_AUTH_NAME = "basic_auth";
    private static final String OAUTH2_NAME = "oauth2_auth";
    private static final String JWT_TOKEN_NAME = "jwt_token_name";

    @Value("${jwt.public.key}")
    RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    RSAPrivateKey privateKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/swagger-ui/**", "v3/api-docs/**"
                , "/login")
            .permitAll()
            .anyRequest()
            .authenticated())
            .httpBasic().disable()
            .oauth2ResourceServer(oauth2ResourceServer ->
                oauth2ResourceServer
                    .jwt(jwt ->
                        jwt.decoder(jwtDecoder())
                    ))
            .build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
        return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(jwk)));
    }

    @Bean
    UserDetailsService userDetailsService(){
        UserDetails userDetails = User.builder().username("user").password(passwordEncoder().encode("password")).build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OpenAPI customOpenAPI(SpringdocConfigProperties springdocConfigProperties) {
        OpenAPI openApi = new OpenAPI()
            .info(new Info().title("custom API Doc").version("this is version")
                .license(new License().name("this is license name")
                    .url("this is license url"))
                .contact(new Contact().name("this is contact name")
                    .email("this is email")));
        if (springdocConfigProperties.isEnabled()) {
            Components component = new Components();
            if (springdocConfigProperties.methods.contains(SpringdocConfigProperties.AuthenticationMethod.BASIC)) {
                component.addSecuritySchemes(BASIC_AUTH_NAME,
                    new SecurityScheme().scheme("basic").type(SecurityScheme.Type.HTTP).name(BASIC_AUTH_NAME));
            }
            if (springdocConfigProperties.methods.contains(SpringdocConfigProperties.AuthenticationMethod.BEARER)) {
                component.addSecuritySchemes(JWT_TOKEN_NAME, new SecurityScheme()
                    .name(JWT_TOKEN_NAME)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT"));
            }
            openApi.components(component)
                .addSecurityItem(new SecurityRequirement().addList(JWT_TOKEN_NAME));
        }
        return openApi;
    }
}

