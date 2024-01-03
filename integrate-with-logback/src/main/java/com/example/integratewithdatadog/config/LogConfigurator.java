//package com.example.integratewithdatadog.config;
//
//import ch.qos.logback.classic.Level;
//import ch.qos.logback.classic.Logger;
//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.classic.PatternLayout;
//import ch.qos.logback.classic.layout.TTLLLayout;
//import ch.qos.logback.classic.spi.Configurator;
//import ch.qos.logback.classic.spi.ConfiguratorRank;
//import ch.qos.logback.classic.spi.ILoggingEvent;
//import ch.qos.logback.core.ConsoleAppender;
//import ch.qos.logback.core.FileAppender;
//import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
//import ch.qos.logback.core.spi.ContextAwareBase;
//import org.springframework.context.annotation.Configuration;
//
//public class LogConfigurator extends ContextAwareBase implements Configurator {
//
//    @Override
//    public ExecutionStatus configure(LoggerContext loggerContext) {
//
//        addInfo("Setting up default configuration.");
//
//        ConsoleAppender<ILoggingEvent> ca = new ConsoleAppender<ILoggingEvent>();
//        ca.setContext(loggerContext);
//        ca.setName("console");
//        LayoutWrappingEncoder<ILoggingEvent> encoder = new LayoutWrappingEncoder<ILoggingEvent>();
//        encoder.setContext(loggerContext);
//
//        PatternLayout layout = new PatternLayout();
//        layout.setPattern("%d{HH:mm:ss.SSS} %green([%thread]) %-5level %logger{36} - %msg%n");
//
//        layout.setContext(loggerContext);
//        layout.start();
//        encoder.setLayout(layout);
//
//        ca.setEncoder(encoder);
//        ca.start();
//
//        FileAppender f=new FileAppender();
//        f.setEncoder(encoder);
//        f.setLayout(layout);
//        f.setContext(loggerContext);
//        f.setFile("log.log");
//        f.setAppend(true);
//        f.setName("logFile");
//        f.start();
//
//
//        Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
//        rootLogger.addAppender(ca);
//        rootLogger.addAppender(f);
//
//        Logger springLogger = loggerContext.getLogger("org.springframework");
//        springLogger.setLevel(Level.WARN);
//
//        // let the caller decide
//        return ExecutionStatus.DO_NOT_INVOKE_NEXT_IF_ANY;
//    }
//}
