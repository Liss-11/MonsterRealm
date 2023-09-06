package com.alissia.monsterService.kafkaProducer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class MonsterProducer {

    private final static Logger logger = LogManager.getLogger(MonsterProducer.class);

    private static MonsterProducer producer;

    private KafkaProducer<String, MonsterCreatedEvent> kafkaProducer;

    private MonsterProducer(){
        try{
            var config = new Properties();
            config.load(new FileReader("MonsterService/src/main/resources/producer.properties"));
            this.kafkaProducer = new KafkaProducer<>(config);
        }catch(IOException e){
            logger.error(e.getMessage());
        }
    }

    public static MonsterProducer getInstance(){
        producer = Objects.nonNull(producer) ? producer : new MonsterProducer();
        return producer;
    }

    private final static String TOPIC = "monster_created_topic";

    private final static Integer PARTITION = 0;

    public void send(String key, MonsterCreatedEvent value){
        try{
            ProducerRecord<String, MonsterCreatedEvent> record = new ProducerRecord<>(TOPIC, PARTITION, key, value);
            this.kafkaProducer.send(record);
        }catch(KafkaException e){
            logger.error(e.getMessage());
            this.close();
        }
    }

    public void close(){
        this.kafkaProducer.close();
    }


}
