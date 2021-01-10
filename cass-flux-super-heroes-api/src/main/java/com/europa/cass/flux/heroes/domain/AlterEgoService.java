package com.europa.cass.flux.heroes.domain;

import java.time.Duration;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Getter
@Setter
public class AlterEgoService {

  private static final Logger logger = LoggerFactory.getLogger(AlterEgoService.class);

  private final String url;
  private final WebClient client;
  private final long delay;

  public AlterEgoService(String url, long delay, WebClient client) {
    this.url = url;
    this.delay = delay;
    this.client = client;
  }

  public Mono<AlterEgoDomain> alterEgo(String id) {
    return this.client
        .get()
        .uri("", id, this.delay)
        .retrieve()
        .bodyToMono(AlterEgoDomain.class)
        .doOnNext(o ->
            logger.info("******* AlterEgoService.alterEgo().doOnNext(): {}", o)
        )
        .timeout(Duration.ofSeconds(30));
  }

}
