package com.alissia.monsterService.service;

import com.alissia.monsterService.MonsterEntity;

import com.alissia.monsterService.MonsterRepository;
import com.alissia.monsterService.dto.CreateMonster;
import com.alissia.monsterService.enums.MonsterStatus;
import com.alissia.monsterService.kafkaConsumer.BattleOutcomeEvent;
import com.alissia.monsterService.kafkaProducer.MonsterCreatedEvent;
import com.alissia.monsterService.kafkaProducer.MonsterProducer;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonsterService implements MonsterServiceInterface {

    private final MonsterRepository monsterRepository;
    public ResponseEntity<String> createMonster(CreateMonster createMonster) {
        try{
            MonsterEntity monster = new MonsterEntity(createMonster.name(), createMonster.type());
            monsterRepository.saveAndFlush(monster);
            MonsterProducer producer = MonsterProducer.getInstance();
            producer.send("monster", new MonsterCreatedEvent(monster.getId(), monster.getName()));
            return new ResponseEntity<>("A new Monster is created with name " + monster.getName(), HttpStatus.CREATED);

        }catch(Exception e){
            log.error(e.getMessage());
        }
        return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<MonsterEntity> getMonsterById(Long id) {
        try{
            Optional<MonsterEntity> monster = monsterRepository.findById(id);
            return monster.map(monsterEntity -> new ResponseEntity<>(monsterEntity, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

        }catch(Exception e){
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<MonsterEntity>> getAllMonsters() {
        List<MonsterEntity> monsters = new ArrayList<>();
        try{
            monsters = monsterRepository.findAll();
            return new ResponseEntity<>(monsters, HttpStatus.OK);
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(monsters, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void updateStatus(BattleOutcomeEvent battleOutcomeEvent) {
        try{
            MonsterEntity monster = monsterRepository.findById(battleOutcomeEvent.monsterId()).orElseThrow(EntityNotFoundException::new);
            if (battleOutcomeEvent.outcome().toString().equals("WON"))
                monster.setStatus(MonsterStatus.WON);
            else
                monster.setStatus(MonsterStatus.LOST);
            monsterRepository.saveAndFlush(monster);
            System.out.println(monsterRepository.findById(battleOutcomeEvent.monsterId()));
        }catch(Exception e){
            //TODO: here I can add a custom exception for Monsters
            e.printStackTrace();
        }
    }


}
