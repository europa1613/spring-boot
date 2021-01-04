package com.wiredbraincoffee.handler;

import com.wiredbraincoffee.model.Product;
import com.wiredbraincoffee.model.ProductEvent;
import com.wiredbraincoffee.repository.ProductRepository;
import java.time.Duration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

  private final ProductRepository repository;

  public ProductHandler(ProductRepository repository) {
    this.repository = repository;
  }

  public Mono<ServerResponse> all(ServerRequest request) {
    Flux<Product> productFlux = repository.findAll();
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(productFlux, Product.class);
  }

  public Mono<ServerResponse> byId(ServerRequest request) {
    String id = request.pathVariable("id");

    Mono<Product> productMono = repository.findById(id);
    Mono<ServerResponse> notFound = ServerResponse.notFound().build();
    return productMono
        .flatMap(product -> ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromPublisher(Mono.just(product), Product.class))
        )
        .switchIfEmpty(notFound);
  }

  public Mono<ServerResponse> save(ServerRequest request) {
    Mono<Product> productMono = request.bodyToMono(Product.class);

    return productMono
        .flatMap(product -> ServerResponse.status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(repository.save(product), Product.class));
  }

  public Mono<ServerResponse> update(ServerRequest request) {
    String id = request.pathVariable("id");
    Mono<Product> productMono = request.bodyToMono(Product.class);
    Mono<Product> repoProduct = repository.findById(id);
    final Mono<ServerResponse> notFound = ServerResponse.notFound().build();
    return productMono.zipWith(repoProduct,
        (product, product2) -> new Product(product2.getId(), product.getName(),
            product.getPrice()))
        .flatMap(product ->
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(repository.save(product), Product.class)
        )
        .switchIfEmpty(notFound);
  }

  public Mono<ServerResponse> delete(ServerRequest request) {
    String id = request.pathVariable("id");
    Mono<Product> repoProduct = repository.findById(id);
    final Mono<ServerResponse> notFound = ServerResponse.notFound().build();
    return repoProduct
        .flatMap(product ->
            ServerResponse.ok()
                .build(repository.deleteById(id)))
        .switchIfEmpty(notFound);
  }

  public Mono<ServerResponse> deleteAll(ServerRequest request) {
    return ServerResponse.ok()
        .build(repository.deleteAll());

  }

  public Mono<ServerResponse> events(ServerRequest request) {
    Flux<ProductEvent> productEventFlux = Flux.interval(Duration.ofSeconds(1))
        .map(val -> new ProductEvent(val, "Fn Product Event: " + val));
    return ServerResponse.ok()
        .contentType(MediaType.TEXT_EVENT_STREAM)
        .body(productEventFlux, ProductEvent.class);
  }

}
