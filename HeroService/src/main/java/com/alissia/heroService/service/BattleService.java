package com.alissia.heroService.service;

import com.alissia.heroService.HeroEntity;
import com.alissia.heroService.HeroRepository;
import com.alissia.heroService.enums.BattleOutcome;
import com.alissia.heroService.kafkaConsumer.MonsterCreatedEvent;
import com.alissia.heroService.kafkaProducer.BattleOutcomeEvent;
import com.alissia.heroService.kafkaProducer.HeroProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BattleService implements BattleServiceInterface {
    
    private final HeroRepository heroRepository;

    private final Random random;

    public void battle(MonsterCreatedEvent monsterCreatedEvent){

        BattleOutcomeEvent battleOutcome = battleOutcome(monsterCreatedEvent);
        battleOutcomeSend(battleOutcome);
    }

    private BattleOutcomeEvent battleOutcome(MonsterCreatedEvent monsterCreatedEvent){

        try{
            List<HeroEntity> heroes = selectRandomHeroes(heroRepository.findAll(), random.nextInt(1, 5));
            BattleOutcome battleOutcome = newBattle(heroes.size());
            if (!heroes.isEmpty() && battleOutcome == BattleOutcome.LOST){
                heroes.forEach(hero -> {
                    HeroEntity myHero = heroRepository.findById(hero.getId()).orElseThrow(IllegalArgumentException::new);
                    myHero.addBattlesFought();
                    heroRepository.save(myHero);
                });
            }
            return new BattleOutcomeEvent(monsterCreatedEvent.monsterId(), battleOutcome);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }

    private BattleOutcome newBattle(Integer numHeroes){

        return switch (numHeroes) {
            case 1 -> (random.nextInt(1, 101) > 25) ? BattleOutcome.WON : BattleOutcome.LOST;
            case 2 -> (random.nextInt(1, 101) > 50) ? BattleOutcome.WON : BattleOutcome.LOST;
            case 3 -> (random.nextInt(1, 101) > 75) ? BattleOutcome.WON : BattleOutcome.LOST;
            case 4 -> BattleOutcome.LOST;
            default -> throw new IllegalArgumentException();
        };
    }

    public void battleOutcomeSend(BattleOutcomeEvent battleOutcome){
        try{
            HeroProducer heroProducer = HeroProducer.getInstance();
            heroProducer.send("hero", battleOutcome);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("battleOutcome Producer don't work as expected");
        }
    }

    private List<HeroEntity> selectRandomHeroes(List<HeroEntity> elementos, int count) {

        if (elementos == null || elementos.isEmpty() || count <= 0) {
            return new ArrayList<>();
        }

        Random random = new Random();
        List<HeroEntity> selectedRandomElements = new ArrayList<>();

        while (selectedRandomElements.size() < count) {
            int randomIndex = random.nextInt(elementos.size());
            HeroEntity selectedElement = elementos.get(randomIndex);

            if (!selectedRandomElements.contains(selectedElement)) {
                selectedRandomElements.add(selectedElement);
            }
        }
        return selectedRandomElements;
    }
}
