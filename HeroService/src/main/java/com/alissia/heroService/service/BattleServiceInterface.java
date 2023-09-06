package com.alissia.heroService.service;

import com.alissia.heroService.kafkaConsumer.MonsterCreatedEvent;
import com.alissia.heroService.kafkaProducer.BattleOutcomeEvent;


public interface BattleServiceInterface {

    void battle(MonsterCreatedEvent monsterCreatedEvent);

    //PRIVATE BattleOutcomeEvent battleOutcome(MonsterCreatedEvent monsterCreatedEvent);

    //PRIVATE: BattleOutcome newBattle(Integer numHeroes);

    void battleOutcomeSend(BattleOutcomeEvent battleOutcome);

     //PRIVATE: List<HeroEntity> selectRandomHeroes(List<HeroEntity> elementos, int count);
}
