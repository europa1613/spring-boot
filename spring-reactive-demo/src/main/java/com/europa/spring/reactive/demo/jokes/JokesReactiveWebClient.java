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


  public static void main(String[] args) throws InterruptedException {

    Instant start = Instant.now();

    Flux.range(1, 3)
        .flatMap(i -> client.get().uri("{count}/jokes?delay=2", 1)
            .retrieve()
            .bodyToMono(Joke[].class))
        .doOnNext(jokes -> logger.info("==============> jokes: {}", Arrays.asList(jokes)))
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

    /*Flux<Joke> jokeFlux = client
        .get()
        .uri("{count}/jokes", 10)
        .retrieve()
        .bodyToFlux(Joke.class)
        .doOnNext(o -> System.out.println("******* GET ALL: " + o));

    Flux.empty()
        .thenMany(jokeFlux)
        .doOnNext(joke -> System.out.println("$$$$$$ joke: " + joke))
        .subscribe(System.out::println);*/

    //Thread.sleep(2000);

    client
        .get()
        .uri("{count}/jokes", 10)
        .retrieve()
        .bodyToFlux(Joke.class)
        .doOnNext(o -> System.out.println("******* GET ALL: " + o))
        .subscribe(System.out::println);

    /*WebClient client2 = WebClient.create("http://localhost:8081/products");
    client2
        .get()
        .retrieve()
        .bodyToFlux(Product.class)
        //.doOnNext(o -> System.out.println("******* GET ALL: " + o))
        .subscribe(product -> System.out.println("############ ALL: " + product));*/


    WebClient client3 = WebClient.create("http://localhost:3001/{count}/jokes?delay={delay}");
    client3
        .get()
        .uri("", 7, 1)
        .retrieve()
        .bodyToFlux(Joke.class)
        .doOnNext(o -> System.out.println("+++++++++++" + o))
        .subscribe(o -> System.out.println("+++++++++++" + o));

    Thread.sleep(3000);
  }

}
