# Getting Started

## start the local kafka environment

start the zookeeper

```
zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
```

start the Kafka

```
kafka-server-start /usr/local/etc/kafka/server.properties
```

check if it is already started.

```
lsof -i :9092
```
then you can call the api to see if kafka message has been sent and consumed through log.

