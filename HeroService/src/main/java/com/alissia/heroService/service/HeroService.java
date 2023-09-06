package com.alissia.heroService.service;

import com.alissia.heroService.HeroEntity;
import com.alissia.heroService.HeroRepository;
import com.alissia.heroService.dto.CreateHero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HeroService implements HeroServiceInterface {

    private final HeroRepository heroRepository;

    @Override
    public ResponseEntity<List<HeroEntity>> getAllHeroes() {
        List<HeroEntity> heroes = new ArrayList<>();
        try{
            heroes = heroRepository.findAll();
            return new ResponseEntity<>(heroes, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(heroes, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> createHero(CreateHero createHero) {
        try{
            HeroEntity hero = new HeroEntity(createHero.name(), createHero.ability());
            heroRepository.save(hero);
            return new ResponseEntity<>("Hero created Successfully", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
