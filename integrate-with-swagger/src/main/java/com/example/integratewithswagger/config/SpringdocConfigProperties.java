package com.example.integratewithswagger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "springdoc.custom.authentication")
@Data
public class SpringdocConfigProperties {
    public boolean enabled;
    public List<AuthenticationMethod> methods;
    public enum AuthenticationMethod {
        BASIC,
        OAUTH2,
        BEARER
    }
}
