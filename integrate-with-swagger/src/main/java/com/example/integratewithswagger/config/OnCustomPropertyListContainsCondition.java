package com.example.integratewithswagger.config;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.List;
import java.util.function.Supplier;

public class OnCustomPropertyListContainsCondition extends SpringBootCondition {

    private static final Bindable<List<String>> STRING_LIST = Bindable.listOf(String.class);

    private final String propertyName;

    private final Supplier<ConditionMessage.Builder> messageBuilder;

    private final String targetValue;

    /**
     * Create a new instance with the property to check and the message builder to use.
     * @param propertyName the name of the property
     * @param messageBuilder a message builder supplier that should provide a fresh
     * instance on each call
     */
    protected OnCustomPropertyListContainsCondition(String propertyName, Supplier<ConditionMessage.Builder> messageBuilder, String targetValue) {
        this.propertyName = propertyName;
        this.messageBuilder = messageBuilder;
        this.targetValue = targetValue;
    }

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        BindResult<?> property = Binder.get(context.getEnvironment()).bind(this.propertyName, STRING_LIST);
        ConditionMessage.Builder messageBuilder = this.messageBuilder.get();
        if (property.isBound() && property.get().toString().contains(this.targetValue)) {
            return ConditionOutcome.match(messageBuilder.found("property").items(this.propertyName));
        }
        return ConditionOutcome.noMatch(messageBuilder.didNotFind("property").items(this.propertyName));
    }
}
