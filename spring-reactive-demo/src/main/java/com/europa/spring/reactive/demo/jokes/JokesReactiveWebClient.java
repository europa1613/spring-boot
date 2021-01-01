package com.europa.spring.reactive.demo.jokes;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class JokesReactiveWebClient {

  private static final Logger logger = LoggerFactory.getLogger(JokesReactiveWebClient.class);

  public static final String JOKES_API_URL = "http://localhost:3001/";

  private static final WebClient client = WebClient.create(JOKES_API_URL);


  public static void main(String[] args) throws JsonProcessingException {

    Instant start = Instant.now();

    List<Mono<Joke[]>> monoList = Stream.of(1, 2, 3)
        .map(i -> client.get().uri("{count}/jokes?delay=2", 1).retrieve().bodyToMono(Joke[].class))
        .collect(
            Collectors.toList());
    Mono.when(monoList).block();

    /*for (int i = 1; i < 4; i++) {
     *//*Arrays.stream(Objects.requireNonNull(client.get().uri("{count}/jokes?delay=2", 1).retrieve()
          .bodyToMono(Joke[].class).block())).forEach(j -> logger.info("====> Jokes: {}", j));*//*
      Mono<Joke[]> jokesMono = client.get().uri("{count}/jokes?delay=2", 1).retrieve()
          .bodyToMono(Joke[].class);
      //jokesMono.subscribe(System.out::println); doesn't work
    }*/
    logger.info("====> Elapsed time: {}ms", Duration.between(start, Instant.now()).toMillis());
  }

}
