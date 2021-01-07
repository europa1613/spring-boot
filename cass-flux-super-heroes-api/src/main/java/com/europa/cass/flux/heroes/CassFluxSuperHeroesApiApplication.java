package com.europa.cass.flux.heroes;

import com.europa.cass.flux.heroes.domain.SuperHeroService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
//@EnableAsync
public class CassFluxSuperHeroesApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(CassFluxSuperHeroesApiApplication.class, args);
  }

  @Value("${api.super.hero.base.uri}")
  private String superHeroUrl;

  @Value("${api.delay}")
  private int apiDelay;

  @Bean
  public SuperHeroService superHeroService() {
    return new SuperHeroService(superHeroUrl, apiDelay, WebClient.create(superHeroUrl));
  }

}

@RestController
@RequestMapping("/ping")
class PingPongController {

  @GetMapping
  public Mono<Map<String, String>> ping() {
    return Mono.just(new HashMap<String, String>() {{
                       put("ping", "pong");
                     }}
    );
  }
}
