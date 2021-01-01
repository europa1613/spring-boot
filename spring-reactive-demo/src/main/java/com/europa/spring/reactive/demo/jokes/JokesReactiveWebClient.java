package com.europa.spring.reactive.demo.jokes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

public class JokesReactiveWebClient {

  private static final Logger logger = LoggerFactory.getLogger(JokesReactiveWebClient.class);

  public static final String JOKES_API_URL = "http://localhost:3001/";

  private static final WebClient client = WebClient.create(JOKES_API_URL);


  public static void main(String[] args) throws JsonProcessingException {

    Instant start = Instant.now();
    for (int i = 1; i < 4; i++) {
      /*Arrays.stream(Objects.requireNonNull(client.get().uri("{count}/jokes?delay=2", 1).retrieve()
          .bodyToMono(Joke[].class).block())).forEach(j -> logger.info("====> Jokes: {}", j));*/
      Mono<Joke[]> jokesMono = client.get().uri("{count}/jokes?delay=2", 1).retrieve()
          .bodyToMono(Joke[].class);
      jokesMono.subscribe(System.out::println);
    }
    logger.info("====> Elapsed time: {}ms", Duration.between(start, Instant.now()).toMillis());
  }

}
