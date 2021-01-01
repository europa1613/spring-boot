package com.europa.spring.reactive.demo.jokes;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class JokesReactiveWebClient {

  private static final Logger logger = LoggerFactory.getLogger(JokesReactiveWebClient.class);

  public static final String JOKES_API_URL = "http://localhost:3001/";

  private static final WebClient client = WebClient.create(JOKES_API_URL);


  public static void main(String[] args) {

    Instant start = Instant.now();

    Flux.range(1, 3)
        .flatMap(i -> client.get().uri("{count}/jokes?delay=2", 1)
            .retrieve()
            .bodyToMono(Joke[].class))
        .doOnNext(jokes -> System.out.println(Arrays.asList(jokes)))
    .blockLast();

   /* List<Mono<Joke[]>> monoList = Stream.of(1, 2, 3)
        .map(i -> client.get().uri("{count}/jokes?delay=2", 1).retrieve().bodyToMono(Joke[].class))
        .collect(
            Collectors.toList());
    Mono.when(monoList).block();*/

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
