package com.alissia.heroService.objectMapper;

import com.alissia.heroService.kafkaProducer.BattleOutcomeEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class BattleOutcomeEventSerializer implements Serializer<BattleOutcomeEvent> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, BattleOutcomeEvent data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new RuntimeException("Error in Serialize the class MonsterCreatedEvent", e);
        }
    }
}
