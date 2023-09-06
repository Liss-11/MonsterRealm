package com.alissia.heroService.objectMapper;

import org.apache.kafka.common.serialization.Deserializer;
import com.alissia.heroService.kafkaConsumer.MonsterCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class MonsterCreatedEventDeserializer implements Deserializer<MonsterCreatedEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No se requiere configuración adicional para este deserializador.
    }

    @Override
    public MonsterCreatedEvent deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, MonsterCreatedEvent.class);
        } catch (Exception e) {
            System.out.println("Error in Deserialize the class MonsterCreatedEvent");
            throw new RuntimeException("Error in Deserialize the class MonsterCreatedEvent", e);
        }
    }

    @Override
    public void close() {
        // No se requiere ninguna operación de cierre para este deserializador.
    }
}
