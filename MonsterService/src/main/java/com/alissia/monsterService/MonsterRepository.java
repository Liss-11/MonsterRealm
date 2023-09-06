package com.alissia.monsterService;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface MonsterRepository extends JpaRepository<MonsterEntity, Long> {
}
