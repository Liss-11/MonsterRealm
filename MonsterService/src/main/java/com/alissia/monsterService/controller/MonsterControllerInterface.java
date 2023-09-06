package com.alissia.monsterService.controller;

import com.alissia.monsterService.MonsterEntity;
import com.alissia.monsterService.dto.CreateMonster;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
public interface MonsterControllerInterface {
    @PostMapping("/monsters")
    ResponseEntity<String> createMonster(@Valid @RequestBody CreateMonster createMonster);

    @GetMapping("/monsters/{id}")
    ResponseEntity<MonsterEntity> getMonsterById(@PathVariable("id") Long id);

    @GetMapping("/monsters")
    ResponseEntity<List<MonsterEntity>> getAllMonsters();
}
