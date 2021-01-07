package com.europa.cass.flux.heroes.controller.orchestrator;

import com.europa.cass.flux.heroes.data.loader.SuperHeroDataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DataLoadOrchestrator {
  private static final Logger logger = LoggerFactory.getLogger(DataLoadOrchestrator.class);

  private final SuperHeroDataLoader superHeroDataLoader;

  public DataLoadOrchestrator(SuperHeroDataLoader superHeroDataLoader) {
    this.superHeroDataLoader = superHeroDataLoader;
  }


  //@Async
  public void start() throws InterruptedException {
    logger.info("================> submit(): start");
    //Thread.sleep(10000);
    /*client
        .get()
        .uri("{count}/jokes?delay=2", 11)
        .retrieve()
        .bodyToFlux(Joke.class)
        .doOnNext(o -> logger.info("*******doOnNext():Joke: {}", o))
        .subscribe(o -> logger.info("*******subscribe():Joke: {}", o));*/

    superHeroDataLoader
        .load()
        .doOnNext(o -> logger.info("******* doOnNext(): Supe: {}", o))
        .subscribe(o -> logger.info("******* subscribe(): Supe: {}", o));

    logger.info("================> submit(): end");
  }

}
