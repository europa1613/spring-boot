package com.europa.cass.flux.heroes;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class CassFluxSuperHeroesApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(CassFluxSuperHeroesApiApplication.class, args);
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
