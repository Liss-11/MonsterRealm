package com.alissia.heroService.service;

import com.alissia.heroService.HeroEntity;
import com.alissia.heroService.dto.CreateHero;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HeroServiceInterface {
    ResponseEntity<List<HeroEntity>> getAllHeroes();

    ResponseEntity<String> createHero(CreateHero createHero);
}
