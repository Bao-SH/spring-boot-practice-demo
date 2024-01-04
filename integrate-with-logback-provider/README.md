# This is a provider, aims to provider integration with logback
To use this, you need: 
1 change the logback-spring.xml to change the config if necessary
2 publish this repo to maven local
3 in logback-consumer module, add the dependency of this repo
4 in application.properties file, add the necessary variables used in logback-spring.xml in this repo
5 test the config works or not
## [not working now] what's the usage of custom configurator? 
The configurator tried to integrate with logback programmatically, to do this:
1 implement the configurator as we do
2 in META-INF.services.ch.qos.logback.classic.spi.Configurator file, input the custom configurator

but for some reason, it doesn't work. not yet found the reason...
## for sensitive words masking, there are several ways
1 to replace directly by regex: see logback-spring.xml the comment out line
2 to mask json input, see logback-spring.xml the dev profile part
3 to mask common input, see logback-spring.xml the dev profile part
