package com.pks.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerApp {

    public static void main(String args[]) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "BROKER-1:9093, BROKER-2:9094, BROKER-3:9095, BROKER-4:9096");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer testProducer = new KafkaProducer(props);

        ProducerRecord testProducerRecord = new ProducerRecord("test_rep_topic", "Example-1001", "Test Message 1");

        testProducer.send(testProducerRecord);
    }
}
