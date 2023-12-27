package com.example.springsecuritylogin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "spring.security.user")
@Data
public class UserDetailsConfig {
    List<UserCredential> userCredentials;

    @Data
    public static class UserCredential {
        String username;
        String password;
        List<UserRole> userRoles;
    }

    public enum UserRole {
        ADMIN,
        USER
    }
}
