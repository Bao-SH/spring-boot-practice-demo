this demo is copied from here:

https://tutorials.solace.dev/spring/spring-cloud-stream/

run the source module and this module.

看solace starter的readme
https://github.com/SolaceProducts/solace-spring-cloud/tree/master/solace-spring-cloud-starters/solace-spring-cloud-stream-starter

重点就是

```
The Solace implementation of the Spring Cloud Stream Binder maps the following concepts from Spring to Solace:
Destinations to topics/subscriptions
Producer bindings always sends messages to topics
Consumer groups to durable queues
A consumer group’s queue is subscribed to its destination subscription (default)
Consumer bindings always receives messages from queues
Anonymous consumer groups to temporary queues (When no group is specified; used for SCS Publish-Subscribe Model)
```


## source config
```yaml
spring:
  cloud:
    function:
      definition: emitSensorReading #define a spring cloud function
    stream:
      poller: #this part should be the interval of each message
        fixed-delay: 5000 #send message every 5 seconds
      bindings:
        emitSensorReading-out-0: #define a binding with one emitSensorReading output destination and the binder
          destination: sensor/temperature/fahrenheit #this decides the output of the message in this function
          binder: local-solace 
      binders:
        local-solace: #define a binder
          type: solace
          environment:
            solace:
              java:
                host: tcp://localhost:55554 #define the local host of solace server
                msgVpn: default
                clientUsername: default
                clientPassword: default
server:
  port: 8096
```

after this config, the message will be sent to sensor/temperature/fahrenheit every 5 seconds

## processor config
```yaml
spring:
  cloud:
    function:
      definition: convertFtoC #define a spring cloud function
    stream:
      default-binder: local-solace 
      bindings:
        convertFtoC-in-0: #define a binding with one convertFtoC input source
          destination: TEMPS.Q
          group: PROCESSOR
        convertFtoC-out-0: #define another binding with one convertFtoC output destination
          destination: sensor/temperature/celsius
      binders:
        local-solace: 
          type: solace
          environment:
            solace:
              java:
                host: tcp://localhost:55554
                msgVpn: default
                clientUsername: default
                clientPassword: default
      solace:
        bindings: #define a binding, for the input source of convertFtoC, subsribe the topic: sensor/temperature/fahrenheit to the queue
          convertFtoC-in-0:
            consumer:
              queueAdditionalSubscriptions: sensor/temperature/fahrenheit
server:
  port: 8081
```
## whole process
根据刚刚starter的重点，整个流程就是
1. source module发消息到topic: sensor/temperature/fahrenheit
2. process module将topic: sensor/temperature/fahrenheit订阅到queue：TEMPS.Q中
3. process module收到queue：TEMPS.Q的消息，进行处理
4. process module将消息发送到topic: sensor/temperature/celsius中
