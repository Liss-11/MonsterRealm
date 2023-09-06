package com.alissia.monsterService.kafkaConsumer;

import com.alissia.monsterService.service.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@RequiredArgsConstructor
public class KafkaListenerHero {

    private final MonsterService monsterService;

    @KafkaListener(topics = "battle_outcome_topic", groupId = "myGroupId", containerFactory = "kafkaListenerContainerFactory")
    private void listener(BattleOutcomeEvent battleOutcomeEvent) {
        System.out.println("Monster id: " + battleOutcomeEvent.monsterId() + "\nMonster status: " + battleOutcomeEvent.outcome().toString());
        monsterService.updateStatus(battleOutcomeEvent);
    }
}
