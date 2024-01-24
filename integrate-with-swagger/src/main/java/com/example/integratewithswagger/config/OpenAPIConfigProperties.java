package com.example.integratewithswagger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "springdoc.custom.authentication")
@Data
public class OpenAPIConfigProperties {
    private boolean enabled;
    private List<AuthenticationMethods> methods;

    public enum AuthenticationMethods {
        BASIC,
        BEARER,
        OAUTH2
    }
}

