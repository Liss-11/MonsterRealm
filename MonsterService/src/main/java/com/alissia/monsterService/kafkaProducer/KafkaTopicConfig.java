package com.alissia.monsterService.kafkaProducer;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    //CONFIGURATION FROM KAFKA DOCUMENTATION
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic("monster_created_topic", 1, (short) 1);
    }

                    //MY CONFIGURATION

    /*@Bean
    public NewTopic MonsterCreatedEvent(){
        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); //DELETE = always delete the messages : COMPACT = saves the last actualization of the message
        //The DEFAULT values is: DELETE = never
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // The period of time than messages will be saved in milliseconds -> in this case wi will save the messages 1 day
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // Max size of the segments in the Topic -> the value is done in BYTES -> in this case we have 1GB (This is also the DEFAULT value)
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "100012"); //The max size of each message, we put this size ("100012") arbitrary! -> the DEFAULT value is 1MB

        return TopicBuilder.name("MonsterCreatedEvent")
                .partitions(1)
                .replicas(1)
                .configs(configurations)
                .build();
    }*/

}
