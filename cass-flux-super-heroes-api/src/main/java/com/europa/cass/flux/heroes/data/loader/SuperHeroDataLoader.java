package com.europa.cass.flux.heroes.data.loader;

import com.europa.cass.flux.heroes.converter.SuperHeroDomainToDtoConverter;
import com.europa.cass.flux.heroes.repository.SuperHeroRepository;
import com.europa.cass.flux.heroes.domain.SuperHeroService;
import com.europa.cass.flux.heroes.dto.SuperHero;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class SuperHeroDataLoader {

  private final SuperHeroService service;

  private final SuperHeroRepository repository;

  public Flux<SuperHero> load() {
    return service
        .heroes()
        .map(SuperHeroDomainToDtoConverter::convert)
        .flatMap(repository::save)
        .thenMany(repository.findAll());
  }

}
