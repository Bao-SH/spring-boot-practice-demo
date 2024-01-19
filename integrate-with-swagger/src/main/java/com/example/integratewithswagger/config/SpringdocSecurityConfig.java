package com.example.integratewithswagger.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;


@SecuritySchemes(value = {
    @SecurityScheme(type = SecuritySchemeType.HTTP, name = "basicAuth", scheme = "basic")
})
@Configuration
public class SpringdocSecurityConfig {
}
