package com.europa.cass.flux.heroes.data.loader;

import com.europa.cass.flux.heroes.converter.SuperHeroDomainToDtoConverter;
import com.europa.cass.flux.heroes.domain.SuperHeroService;
import com.europa.cass.flux.heroes.dto.SuperHero;
import com.europa.cass.flux.heroes.dto.SuperHeroPrimaryKey;
import com.europa.cass.flux.heroes.repository.SuperHeroRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.EntityWriteResult;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.data.cassandra.core.ReactiveCassandraOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SuperHeroDataLoader {

  private final SuperHeroService service;

  private final SuperHeroRepository repository;

  @Autowired
  private ReactiveCassandraOperations reactiveCassandraOperations;

  public Flux<SuperHero> load() {
    return service
        .heroes()
        .map(SuperHeroDomainToDtoConverter::convert)
        .flatMap(repository::save)
        .thenMany(repository.findAll());
  }

  public Mono<SuperHero> saveWithTTL() {
    SuperHeroPrimaryKey key = new SuperHeroPrimaryKey(10, "Batman", LocalDate.parse("1975-10-13"));
    SuperHero batman = new SuperHero(key, LocalDateTime.now());
    return reactiveCassandraOperations
        .insert(batman, InsertOptions.builder().ttl(60).build())
        .map(EntityWriteResult::getEntity);
  }

}
