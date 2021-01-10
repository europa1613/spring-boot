package com.europa.cass.flux.heroes.repository;

import com.europa.cass.flux.heroes.dto.SuperHero;
import com.europa.cass.flux.heroes.dto.SuperHeroPrimaryKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SuperHeroRepository extends
    ReactiveCassandraRepository<SuperHero, SuperHeroPrimaryKey> {

  @Query("SELECT DISTINCT ID FROM HEROES")
  Flux<Long> distinctPersonIds();

}
