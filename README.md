# Kafka

 Apache Kafka provides the messaging infrastructure of these and many more massive software as a service applications we use every day
 
## Start Zookeeper
`$ ./bin/zookeeper-server-start.sh config/zookeeper.properties`

## Start Message Broker/Kafka server
`$ ./bin/kafka-server-start.sh config/server.properties`

## Create a topic
`$ ./bin/kafka-topics.sh  --create --topic my_topic --zookeeper localhost:2181 --replication-factor 1 --partitions 1`

## List the topics in a particular cluster or zookeeper
`$ ./bin/kafka-topics.sh --list --zookeeper localhost:2181`

## Start kafka producer
`$ ./bin/kafka-console-producer.sh --broker-list localhost:9092 --topic my_topic`

## Start Kafka consumer
`$ ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my_topic --from-beginning`

## Create multi broker cluster
We will start with 4 brokers. For each broker we need to create a new config file same as `/config/server.properties`. For 4 broker we have created 4 broker config files, `server-1.properties`, `server-2.properties`, `server-3.properties` and `server-4.properties`. In each config file we need to change the broker id, listeners and log directory.
```
config/server-1.properties
	broker.id=1
    listeners=PLAINTEXT://:9093
    log.dirs=/tmp/kafka-logs-1

config/server-2.properties
	broker.id=2
    listeners=PLAINTEXT://:9094
    log.dirs=/tmp/kafka-logs-2

config/server-3.properties
	broker.id=3
    listeners=PLAINTEXT://:9095
    log.dirs=/tmp/kafka-logs-3

config/server-4.properties
	broker.id=4
    listeners=PLAINTEXT://:9096
    log.dirs=/tmp/kafka-logs-4         

```

Commands to start brokers:

`$ ./bin/kafka-server-start.sh config/server-1.properties`

`$ ./bin/kafka-server-start.sh config/server-2.properties`

`$ ./bin/kafka-server-start.sh config/server-3.properties`

`$ ./bin/kafka-server-start.sh config/server-4.properties`

## Create Replication

`$ ./bin/kafka-topics.sh  --create --topic test_rep_topic --zookeeper localhost:2181 --replication-factor 4 --partitions 1`

Check details of the topic
```
$ ./bin/kafka-topics.sh --describe --topic test_rep_topic --zookeeper localhost:2181

Topic: test_rep_topic   PartitionCount:1    ReplicationFactor:4 Configs:
Topic: test_rep_topic   Partition: 0        Leader: 1           Replicas: 1,2,3,4   Isr: 1,2,3,4

```
According to the output node/server **1** is the leader. If there are more partitions then there would have been more rows.

## Create Producer/Consumer that publishes/consumes message to/from multiple brokers

```
$ ./bin/kafka-console-producer.sh --broker-list localhost:9093 localhost:9095 --topic test_topic_123

$ ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9093 localhost:9095 localhost:9094 --topic test_topic_123 --from-beginning 
```
