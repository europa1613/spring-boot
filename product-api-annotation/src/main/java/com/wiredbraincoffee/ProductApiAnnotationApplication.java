package com.wiredbraincoffee;

import com.wiredbraincoffee.model.Product;
import com.wiredbraincoffee.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ProductApiAnnotationApplication {

  private static final Logger logger = LoggerFactory
      .getLogger(ProductApiAnnotationApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ProductApiAnnotationApplication.class, args);
  }

  @Bean
  public CommandLineRunner init(ProductRepository repository) {
    return args -> {
      Flux<Product> productFlux = Flux.just(new Product(null, "Big Latte", 2.99),
          new Product(null, "Big Decaf", 2.49),
          new Product(null, "Green Tea", 1.99)
      )
          .flatMap(repository::save);
      productFlux.thenMany(repository.findAll())
          .subscribe(product -> logger.info("Product: {}", product));
    };
  }

}
