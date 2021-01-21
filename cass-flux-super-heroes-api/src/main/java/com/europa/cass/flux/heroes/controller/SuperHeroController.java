package com.europa.cass.flux.heroes.controller;

import com.europa.cass.flux.heroes.controller.orchestrator.DataLoadOrchestrator;
import com.europa.cass.flux.heroes.data.loader.SuperHeroDataLoader;
import com.europa.cass.flux.heroes.dto.SuperHero;
import com.europa.cass.flux.heroes.repository.SuperHeroRepository;
import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.EntityWriteResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/supes")
public class SuperHeroController {

  private final DataLoadOrchestrator orchestrator;

  @Autowired
  SuperHeroRepository repository;

  @Autowired
  SuperHeroDataLoader superHeroDataLoader;

  public SuperHeroController(DataLoadOrchestrator orchestrator) {
    this.orchestrator = orchestrator;
  }

  @GetMapping("/load")
  public ResponseEntity<DataLoadResponse> submit()
      throws InterruptedException {
    orchestrator.start();
    return ResponseEntity.accepted().body(new DataLoadResponse("PROCESSING"));
  }

  @GetMapping("/ids")
  public Flux<Long> ids() {
    return repository.distinctPersonIds()
        .delayElements(Duration.ofMillis(500));
  }

  @GetMapping("/insert/ttl")
  public Mono<SuperHero> ttl() {
    return superHeroDataLoader.saveWithTTL();
  }


}

@Data
@NoArgsConstructor
@AllArgsConstructor
class DataLoadResponse {

  private String status;
}
