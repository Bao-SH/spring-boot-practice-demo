package com.example.integratewithswagger.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.*;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "User API", version = "version",
                contact = @Contact(name = "Baeldung", email = "user-apis@baeldung.com", url = "https://www.baeldung.com"),
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"), termsOfService = "${tos.uri}",
                description = "description"))
@EnableConfigurationProperties(OpenAPIConfigProperties.class)
@RequiredArgsConstructor
public class OpenAPIConfiguration {

    private static final String BASIC_AUTH = "basicAuth";
    private static final String BEARER_AUTH = "bearerAuth";
    private static final String OAUTH_2_AUTH = "oauth2";

    private final OpenAPIConfigProperties openAPIConfigProperties;

    /**
     * Configure the global OpenAPI components.
     *
     * @return Returns fully configure OpenAPI object
     * @see OpenAPI
     */

    @Bean
    GroupedOpenApi customGroupedOpenApi() {
        return GroupedOpenApi.builder().group(BASIC_AUTH)
            .pathsToMatch("/api/auth/**")
            .addOpenApiCustomizer(customizer -> customizer.components(new Components()
                .addSecuritySchemes(BASIC_AUTH, new SecurityScheme().name(BASIC_AUTH)
                .scheme("basic")
                .type(SecurityScheme.Type.HTTP)))
                .addSecurityItem(new SecurityRequirement().addList(BASIC_AUTH)))
            .build();
    }

    @Bean
    @Conditional(OpenAPIAuthenticationMethodsContainsBearerCondition.class)
    GroupedOpenApi jwtGroupedOpenApi() {
        return GroupedOpenApi.builder().group(BEARER_AUTH)
            .pathsToExclude("/api/auth/**")
            .addOpenApiCustomizer(customizer -> customizer.components(new Components()
                    .addSecuritySchemes(BEARER_AUTH, new SecurityScheme()
                        .name(BEARER_AUTH)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .description(
                            "Provide the JWT token. JWT token can be obtained from the Login API. For testing, use the credentials <strong>john/password</strong>")
                        .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList(BEARER_AUTH)))
            .build();
    }

    @Bean
    GroupedOpenApi oauth2GroupedOpenApi() {
        return GroupedOpenApi.builder().group("OAUTH_2")
            .pathsToExclude("")
            .addOpenApiCustomizer(customizer -> customizer.components(
                new Components()
                    .addSecuritySchemes("OAUTH_2", new SecurityScheme()
                        .name("OAUTH_2")
                        .type(SecurityScheme.Type.OAUTH2)
                        .flows(new OAuthFlows()
                            .authorizationCode(new OAuthFlow().authorizationUrl("https://dev-0hkzrvxf7v20p0s8.us.auth0.com/authorize")
                                .tokenUrl("https://dev-0hkzrvxf7v20p0s8.us.auth0.com/oauth/token")
                                .scopes(new Scopes().addString("read:api", "read:api")))
                            )))
                .addSecurityItem(new SecurityRequirement().addList("OAUTH_2")))
            .build();
    }
}
