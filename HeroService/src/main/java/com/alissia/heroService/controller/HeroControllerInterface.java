package com.alissia.heroService.controller;

import com.alissia.heroService.HeroEntity;
import com.alissia.heroService.dto.CreateHero;
//import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
public interface HeroControllerInterface {

    @PostMapping("/heroes")
    ResponseEntity<String> createHero(@Valid @RequestBody CreateHero createHero);

    @PutMapping("/heroes/{id}")
    ResponseEntity<String> updateHero(@PathVariable ("id") Long id);

    @GetMapping("/heroes/{id}")
    ResponseEntity<HeroEntity> getHeroById(@PathVariable ("id") Long id);

    @GetMapping("/heroes")
    ResponseEntity<List<HeroEntity>> getAllHeroes();

    @DeleteMapping("/heroes/{id}")
    ResponseEntity<String> deleteHero(@PathVariable ("id") Long id);
}