package com.europa.spring.reactive.demo;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
class SpringReactiveDemoApplicationTests {

  @Autowired
  private CoffeeService service;

  @Test
  void contextLoads() {
  }

  @Test
  void getCoffeeOrders() {
    String coffeeId = service.getAllCoffees().blockFirst().getId();

    StepVerifier.withVirtualTime(() -> service.getOrders(coffeeId).take(10))
        .thenAwait(Duration.ofHours(10))
        .expectNextCount(10)
        .expectComplete()
        .verify();
  }

}
