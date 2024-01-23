package com.example.integratewithswagger.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "User API", version = "version",
                contact = @Contact(name = "Baeldung", email = "user-apis@baeldung.com", url = "https://www.baeldung.com"),
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"), termsOfService = "${tos.uri}",
                description = "description"))
@EnableConfigurationProperties(ConfigProperties.class)
public class OpenAPIConfiguration {


    private static final String BASIC_AUTH = "basicAuth";
    private static final String BEARER_AUTH = "bearerAuth";
    private static final String OAUTH_2_AUTH = "oauth2";

    /**
     * Configure the OpenAPI components.
     *
     * @return Returns fully configure OpenAPI object
     * @see OpenAPI
     */
//    @Bean
//    public OpenAPI customizeOpenAPI(ConfigProperties configProperties) {
//        Components components = new Components();
//        OpenAPI openAPI = new OpenAPI();
//        if (configProperties.isEnabled()) {
//            if (configProperties.getMethods().contains(ConfigProperties.AuthenticationMethods.BASIC)) {
//                components.addSecuritySchemes(BASIC_AUTH, new SecurityScheme().name(BASIC_AUTH)
//                    .scheme("basic")
//                    .type(SecurityScheme.Type.HTTP));
//                openAPI.addSecurityItem(new SecurityRequirement().addList(BASIC_AUTH));
//            }
//            if (configProperties.getMethods().contains(ConfigProperties.AuthenticationMethods.BEARER)) {
//                components
//                    .addSecuritySchemes(BEARER_AUTH, new SecurityScheme()
//                        .name(BEARER_AUTH)
//                        .type(SecurityScheme.Type.HTTP)
//                        .scheme("bearer")
//                        .description(
//                            "Provide the JWT token. JWT token can be obtained from the Login API. For testing, use the credentials <strong>john/password</strong>")
//                        .bearerFormat("JWT"));
//                openAPI.addSecurityItem(new SecurityRequirement().addList(BEARER_AUTH));
//            }
//            if (configProperties.getMethods().contains(ConfigProperties.AuthenticationMethods.OAUTH2)) {
//                components.addSecuritySchemes(OAUTH_2_AUTH, new SecurityScheme().name(OAUTH_2_AUTH)
//                    .type(SecurityScheme.Type.OAUTH2)
//                    .flows(new OAuthFlows()));
//                openAPI.addSecurityItem(new SecurityRequirement().addList(OAUTH_2_AUTH));
//            }
//        }
//        return openAPI.components(components);
//    }

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
}
