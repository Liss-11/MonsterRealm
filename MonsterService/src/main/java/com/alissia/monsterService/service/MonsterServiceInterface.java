package com.alissia.monsterService.service;

import com.alissia.monsterService.MonsterEntity;
import com.alissia.monsterService.dto.CreateMonster;
import com.alissia.monsterService.kafkaConsumer.BattleOutcomeEvent;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MonsterServiceInterface {

    ResponseEntity<String> createMonster(CreateMonster createMonster);

    ResponseEntity<MonsterEntity> getMonsterById(Long id);

    ResponseEntity<List<MonsterEntity>> getAllMonsters();

    void updateStatus(BattleOutcomeEvent battleOutcomeEvent);
}
