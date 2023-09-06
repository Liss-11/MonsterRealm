package com.alissia.heroService;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
@RequiredArgsConstructor
public class CreateHeroesConfiguration {

    private final HeroRepository heroRepository;

    @Bean
    Random random(){
        return new Random();
    }

    @Bean
    public void createHeroes(){
        HeroEntity hero1 = new HeroEntity("Loren", "Swordsman");
        HeroEntity hero2 = new HeroEntity("Maria", "Speed");
        HeroEntity hero3 = new HeroEntity("Isabel", "Flexible");
        HeroEntity hero4 = new HeroEntity("Pol", "Silent");
        HeroEntity hero5 = new HeroEntity("Marc", "Cunning");
        HeroEntity hero6 = new HeroEntity("Max", "Strong");
        HeroEntity hero7 = new HeroEntity("Clara", "Intelligent");
        heroRepository.save(hero1);
        heroRepository.save(hero2);
        heroRepository.save(hero3);
        heroRepository.save(hero4);
        heroRepository.save(hero5);
        heroRepository.save(hero6);
        heroRepository.save(hero7);
    }
}
