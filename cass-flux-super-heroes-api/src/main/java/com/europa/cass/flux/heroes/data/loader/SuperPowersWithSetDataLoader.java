package com.europa.cass.flux.heroes.data.loader;

import com.europa.cass.flux.heroes.converter.AlterEgoDomainToDtoConverter;
import com.europa.cass.flux.heroes.converter.SuperPowersDomainToDTOWithSetConverter;
import com.europa.cass.flux.heroes.domain.AlterEgoService;
import com.europa.cass.flux.heroes.domain.FluxSuperPowersService;
import com.europa.cass.flux.heroes.dto.AlterEgo;
import com.europa.cass.flux.heroes.dto.SuperPowersWithSet;
import com.europa.cass.flux.heroes.repository.AlterEgoRepository;
import com.europa.cass.flux.heroes.repository.SuperPowersWithSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class SuperPowersWithSetDataLoader {

  private final FluxSuperPowersService service;

  private final SuperPowersWithSetRepository repository;

  public Flux<SuperPowersWithSet> load(String id) {
    return service
        .powers()
        .map(SuperPowersDomainToDTOWithSetConverter::convert)
        .flatMap(repository::save)
        .thenMany(repository.findAll());
  }

}
