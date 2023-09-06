package com.alissia.monsterService.objectMapper;

import com.alissia.monsterService.kafkaConsumer.BattleOutcomeEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class HeroBattleOutcomeEventDeserializer implements Deserializer<BattleOutcomeEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public BattleOutcomeEvent deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, BattleOutcomeEvent.class);
        } catch (Exception e) {
            System.out.println("Error in Deserialize the class MonsterCreatedEvent");
            throw new RuntimeException("Error in Deserialize the class MonsterCreatedEvent", e);
        }
    }

    @Override
    public void close() {}
}
