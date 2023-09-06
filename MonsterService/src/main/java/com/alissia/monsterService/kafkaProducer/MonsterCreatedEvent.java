package com.alissia.monsterService.kafkaProducer;

public record MonsterCreatedEvent(Long monsterId, String name) { }
