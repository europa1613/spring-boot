package com.wiredbraincoffee.controller;

import com.wiredbraincoffee.model.Product;
import com.wiredbraincoffee.model.ProductEvent;
import com.wiredbraincoffee.repository.ProductRepository;
import java.time.Duration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductRepository repository;

  public ProductController(ProductRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public Flux<Product> all() {
    return repository.findAll();
  }

  @GetMapping("{id}")
  public Mono<ResponseEntity<Product>> byId(@PathVariable String id) {
    return repository.findById(id).map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build())
        ;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Product> save(@RequestBody Product p) {
    return repository.save(p);
  }

  @PutMapping("{id}")
  public Mono<ResponseEntity<Product>> update(@PathVariable String id, @RequestBody Product p) {
    return repository.findById(id)
        .flatMap(product -> {
          product.setName(p.getName());
          product.setPrice(p.getPrice());
          return repository.save(product);
        }).map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("{id}")
  public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
    return repository.findById(id)
        .flatMap(product -> repository.delete(product)
            .then(Mono.just(ResponseEntity.ok().<Void>build())))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping
  public Mono<Void> deleteAll() {
    return repository.deleteAll();
  }

  @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ProductEvent> events() {
    return Flux.interval(Duration.ofSeconds(1))
        .map(val -> new ProductEvent(val, "Product Event " + val));
  }

}
