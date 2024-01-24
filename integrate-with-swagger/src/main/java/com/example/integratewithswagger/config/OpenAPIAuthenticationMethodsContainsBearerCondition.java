package com.example.integratewithswagger.config;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;

class OpenAPIAuthenticationMethodsContainsBearerCondition extends OnCustomPropertyListContainsCondition {
    OpenAPIAuthenticationMethodsContainsBearerCondition() {
        super("springdoc.custom.authentication.methods",
            () -> ConditionMessage.forCondition("springdoc.custom.authentication.methods"),
            "BEARER");
    }
}
