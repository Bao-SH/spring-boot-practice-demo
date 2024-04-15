package com.example.dynamicproxydemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicInvocationHandler implements InvocationHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(DynamicInvocationHandler.class);

    private Object target;

    public DynamicInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOGGER.info("Invoked method: {}, from class: {}", method.getName(), target.getClass().getName());
        method.invoke(target, args);
        return null;
    }
}
