package com.europa.cass.flux.heroes.domain;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@Getter
@Setter
public class SuperHeroService {

  private static final Logger logger = LoggerFactory.getLogger(SuperHeroService.class);

  private final String url;
  private final WebClient client;
  private final long delay;

  public SuperHeroService(String url, long delay, WebClient client) {
    this.url = url;
    this.delay = delay;
    this.client = client;
  }

  public Flux<SuperHeroDomain> heroes() {
    AtomicLong count = new AtomicLong();
    return this.client
        .get()
        .uri("", this.delay)
        .retrieve()
        .bodyToFlux(SuperHeroDomain.class)
        .doOnNext(o -> {
          count.incrementAndGet();
          logger.info("******* SuperHeroService.heroes().doOnNext(): {}", o);
        })
        .timeout(Duration.ofSeconds(30))
        .doOnComplete(
            () -> logger.info("******* SuperHeroService.heroes().doOnComplete(): {}", count));
  }

}
