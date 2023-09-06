package com.alissia.heroService.controller;

import com.alissia.heroService.HeroEntity;
import com.alissia.heroService.dto.CreateHero;
import com.alissia.heroService.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HeroController implements HeroControllerInterface{

    private final HeroService heroService;

    @Override
    public ResponseEntity<String> createHero(CreateHero createHero) {
        return heroService.createHero(createHero);
    }

    @Override
    public ResponseEntity<String> updateHero(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<HeroEntity> getHeroById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<HeroEntity>> getAllHeroes() {
        return heroService.getAllHeroes();
    }

    @Override
    public ResponseEntity<String> deleteHero(Long id) {
        return null;
    }

}
