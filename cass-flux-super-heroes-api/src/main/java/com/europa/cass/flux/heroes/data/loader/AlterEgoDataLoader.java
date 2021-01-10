package com.europa.cass.flux.heroes.data.loader;

import com.europa.cass.flux.heroes.converter.AlterEgoDomainToDtoConverter;
import com.europa.cass.flux.heroes.domain.AlterEgoService;
import com.europa.cass.flux.heroes.dto.AlterEgo;
import com.europa.cass.flux.heroes.repository.AlterEgoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class AlterEgoDataLoader {

  private final AlterEgoService service;

  private final AlterEgoRepository repository;

  public Flux<AlterEgo> load(String id) {
    return service
        .alterEgo(id)
        .map(AlterEgoDomainToDtoConverter::convert)
        .flatMap(repository::save)
        .thenMany(repository.findAll());
  }

}
