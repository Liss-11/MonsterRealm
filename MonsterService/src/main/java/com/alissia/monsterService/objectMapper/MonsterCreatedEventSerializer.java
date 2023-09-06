package com.alissia.monsterService.objectMapper;

import com.alissia.monsterService.kafkaProducer.MonsterCreatedEvent;
import org.apache.kafka.common.serialization.Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MonsterCreatedEventSerializer implements Serializer<MonsterCreatedEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, MonsterCreatedEvent data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new RuntimeException("Error in Serialize the class MonsterCreatedEvent", e);
        }
    }
}
