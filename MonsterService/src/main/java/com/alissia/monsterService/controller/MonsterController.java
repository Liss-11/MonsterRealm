package com.alissia.monsterService.controller;

import com.alissia.monsterService.MonsterEntity;
import com.alissia.monsterService.dto.CreateMonster;
import com.alissia.monsterService.service.MonsterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MonsterController implements MonsterControllerInterface {

    private final MonsterService monsterService;

    @Override
    public ResponseEntity<String> createMonster(@Valid @RequestBody CreateMonster createMonster){
        return monsterService.createMonster(createMonster);
    }

    @Override
    public ResponseEntity<MonsterEntity> getMonsterById(@PathVariable ("id") Long id){
        return monsterService.getMonsterById(id);
    }

    @Override
    public ResponseEntity<List<MonsterEntity>> getAllMonsters(){
        return monsterService.getAllMonsters();
    }
}
