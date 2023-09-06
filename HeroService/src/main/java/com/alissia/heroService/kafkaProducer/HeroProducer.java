package com.alissia.heroService.kafkaProducer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;

import java.io.FileReader;
import java.util.Objects;
import java.util.Properties;

public class HeroProducer {

    private static HeroProducer heroProducer;

    private KafkaProducer<String, BattleOutcomeEvent> kafkaProducer;

    private HeroProducer(){
        try{
            var configuration = new Properties();
            configuration.load(new FileReader("HeroService/src/main/resources/producer.properties"));
            this.kafkaProducer = new KafkaProducer<>(configuration);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static HeroProducer getInstance(){
        heroProducer = Objects.nonNull(heroProducer) ? heroProducer : new HeroProducer();
        return heroProducer;
    }

    private final static String TOPIC = "battle_outcome_topic";

    private final static Integer PARTITION = 0;

    public void send(String key, BattleOutcomeEvent value){
        try{
            ProducerRecord<String, BattleOutcomeEvent> record = new ProducerRecord<>(TOPIC, PARTITION, key, value);
            this.kafkaProducer.send(record);
        }catch(KafkaException e){
            e.printStackTrace();
            this.close();
        }
    }

    public void close(){
        this.kafkaProducer.close();
    }


}
