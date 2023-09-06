package com.alissia.monsterService;

import com.alissia.monsterService.enums.MonsterStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "monsters")
public class MonsterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @Enumerated(EnumType.STRING)
    private MonsterStatus status = MonsterStatus.NEW;

    public MonsterEntity(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
