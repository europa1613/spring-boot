package com.example;

import java.time.Duration;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Mono;

public class BlockHoundExample {

  public static void main(String[] args) {
    BlockHound
        //.builder()
        //.addDynamicThreadPredicate(Thread::isDaemon) //Doesn't work
        .install();

    Mono.delay(Duration.ofSeconds(1))
        .doOnNext(it -> {
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        })
        .block();
  }

}
