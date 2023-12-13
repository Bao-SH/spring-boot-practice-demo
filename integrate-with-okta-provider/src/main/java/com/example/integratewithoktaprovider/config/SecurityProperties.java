package com.example.integratewithoktaprovider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "security")
@Data
public class SecurityProperties {
    private List<String> endpoints;
}
