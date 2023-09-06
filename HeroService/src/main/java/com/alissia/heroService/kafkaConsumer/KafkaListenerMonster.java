package com.alissia.heroService.kafkaConsumer;

import com.alissia.heroService.service.BattleService;
import com.alissia.heroService.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@RequiredArgsConstructor
public class KafkaListenerMonster {

    private final BattleService battleService;

    @KafkaListener(topics = "monster_created_topic", groupId = "myGroupId", containerFactory = "kafkaListenerContainerFactory")
    private void listener(MonsterCreatedEvent monsterCreatedEvent) {
        System.out.println("Monster id: " + monsterCreatedEvent.monsterId() + "\nMonster name: " + monsterCreatedEvent.name());
        battleService.battle(monsterCreatedEvent);
    }

}
