package com.pks.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerApp {

    public static void main(String args[]) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093, localhost:9094, localhost:9095, localhost:9096");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer kafkaProducer = new KafkaProducer(props);

        try{
            for(int i = 0; i < 10; i++){
                System.out.println(i);
                kafkaProducer.send(new ProducerRecord("test_topic_123", "Example-1001", "Message - " + i ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            kafkaProducer.close();
        }
    }
}
