package com.alissia.heroService.kafkaConsumer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record MonsterCreatedEvent(Long monsterId, String name) {
    @JsonCreator
    public MonsterCreatedEvent(
            @JsonProperty("monsterId") Long monsterId,
            @JsonProperty("name") String name) {
        this.monsterId = monsterId;
        this.name = name;
    }

}
