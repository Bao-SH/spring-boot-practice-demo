//package com.example.springsecuritylogin.config;
//
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//import java.util.Map;
//
//@Data
//@Component
//@ConfigurationProperties(prefix = "spring.security")
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    private Map<String, BasicAuthCredential> basicCredentials;
//
//    @Override
//    public Authentication authenticate(Authentication auth) throws AuthenticationException {
//        if (!(auth instanceof UsernamePasswordAuthenticationToken)) {
//            return null;
//        }
//        var inputUserName = auth.getName();
//        var inputPassword = auth.getCredentials().toString();
//
//        return basicCredentials.values().stream()
//            .filter(basicAuthCredential -> inputUserName.equals(basicAuthCredential.username))
//            .filter(basicAuthCredential -> inputPassword.equals(basicAuthCredential.password))
//            .map(basicAuthCredential -> new UsernamePasswordAuthenticationToken(inputUserName, inputPassword, Collections.emptyList()))
//            .findAny()
//            .orElseThrow(() -> new BadCredentialsException("Authentication failed."));
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
//    }
//
//
//    @Data
//    static class BasicAuthCredential {
//        String username;
//        String password;
//    }
//
//}
