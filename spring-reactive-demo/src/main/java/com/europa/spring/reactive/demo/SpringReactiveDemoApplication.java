package com.europa.spring.reactive.demo;

import java.time.Instant;
import java.util.UUID;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringReactiveDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringReactiveDemoApplication.class, args);
  }

}

@Component
class DataLoader {

  private final CoffeeRepository repo;

  public DataLoader(CoffeeRepository repo) {
    this.repo = repo;
  }

  @PostConstruct
  private void loadData() {
    Flux.just("Americano", "Esmeralda", "Kaldi's Coffee", "Café Olé", "Delta", "Java")
        .map(name -> new Coffee(UUID.randomUUID().toString(), name))
        .flatMap(repo::save)
        .subscribe(System.out::println);
  }
}

interface CoffeeRepository extends ReactiveCrudRepository<Coffee, String> {

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class CoffeeOrder {

  private String coffeeId;
  private Instant dateOrdered;
}

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
class Coffee {

  @Id
  private String id;
  private String name;
}
