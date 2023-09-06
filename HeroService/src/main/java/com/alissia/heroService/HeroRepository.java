package com.alissia.heroService;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HeroRepository extends MongoRepository<HeroEntity, Long> {


}
