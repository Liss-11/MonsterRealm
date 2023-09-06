package com.alissia.heroService;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "heroes")
public class HeroEntity {
    @Id
    private Long id;
    private String name;
    private String ability;
    private Integer battlesFought;
    private static Long idAutoincrement = 0L;

    //TODO: las batallas ganadas se iran sumando, con un update de la database.

    public HeroEntity(String name, String ability) {
        idAutoincrement = idAutoincrement + 1L;
        this.id = idAutoincrement;
        this.name = name;
        this.ability = ability;
        this.battlesFought = 0;
    }

     public void addBattlesFought(){
        this.battlesFought++;
    }
}
