# Getting Started

## Need to run solace broker locally
link:  https://docs.solace.com/Software-Broker/SW-Broker-Set-Up/Containers/Set-Up-Docker-Container-macOS.htm

command: 
```docker-compose -f PubSubStandard_singleNode.yml up -d```

and you need to see if the port is correct:
for example, in my docker:
```
CONTAINER ID   IMAGE                                  COMMAND               CREATED        STATUS          PORTS                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 NAMES
770c82165c94   solace/solace-pubsub-standard:latest   "/usr/sbin/boot.sh"   24 hours ago   Up 32 seconds   0.0.0.0:1443->1443/tcp, :::1443->1443/tcp, 0.0.0.0:1883->1883/tcp, :::1883->1883/tcp, 0.0.0.0:1943->1943/tcp, :::1943->1943/tcp, 0.0.0.0:2222->2222/tcp, :::2222->2222/tcp, 0.0.0.0:5671-5672->5671-5672/tcp, :::5671-5672->5671-5672/tcp, 0.0.0.0:8000->8000/tcp, :::8000->8000/tcp, 0.0.0.0:8008->8008/tcp, :::8008->8008/tcp, 0.0.0.0:8080->8080/tcp, :::8080->8080/tcp, 0.0.0.0:8443->8443/tcp, :::8443->8443/tcp, 0.0.0.0:8883->8883/tcp, :::8883->8883/tcp, 0.0.0.0:9000->9000/tcp, :::9000->9000/tcp, 0.0.0.0:9443->9443/tcp, :::9443->9443/tcp, 0.0.0.0:55003->55003/tcp, :::55003->55003/tcp, 0.0.0.0:55443->55443/tcp, :::55443->55443/tcp, 0.0.0.0:55554->55555/tcp, :::55554->55555/tcp   pubSubStandardSingleNode
```
smf port is like below: 55554:55555

so in application.properties, ```solace.jms.host=localhost:55554```

## Config your broker
in my example, i have two queues:
- tutorial/testqueue: subscribe two topics: topic-1 and topic-2
- secondqueue: subsribe one topic: topic-3
## Send message to both queue and topic
JMS will send message to queue by default.
if you'd like to send message to topic, you need to have this config:
```jmsTemplate.setPubSubDomain(true);```
## Listen to both queue and topic
If you only want to listen to queue, it is supported by default.

If you want to listen to only topic, you can config 
```spring.jms.pub-sub-domain=true``` in your application.properties file.
It works globally.

If you want to listen to both queue and topic, you can define your CustomMessageListenerContainer bean:
```java
    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        factory.setPubSubDomain(true);
        return factory;
    }
```
and use it in @JmsListener:
```@JmsListener(destination = "${solace.spring.secondTopicName}", containerFactory = "myFactory")```

## Expect result
because tutorial/testqueue subscribes topic-1, so two messages will be received:
```Payload: Hello World to the tutorial/testqueue```
```Payload: Hello World to the topic-1```
and secondqueue subscribes topic-3, so one message will be received:
```Payload: Hello World to the topic-3```