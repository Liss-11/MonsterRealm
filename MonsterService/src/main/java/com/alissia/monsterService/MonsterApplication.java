package com.alissia.monsterService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MonsterApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonsterApplication.class, args);
    }

    @Bean
    public Logger logger(){
        return LogManager.getLogger();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}