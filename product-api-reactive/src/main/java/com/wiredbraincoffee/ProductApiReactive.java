package com.wiredbraincoffee;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RequestPredicates.method;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.wiredbraincoffee.handler.ProductHandler;
import com.wiredbraincoffee.model.Product;
import com.wiredbraincoffee.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ProductApiReactive {

  private static final Logger logger = LoggerFactory
      .getLogger(ProductApiReactive.class);

  public static void main(String[] args) {
    SpringApplication.run(ProductApiReactive.class, args);
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

  @Bean
  RouterFunction<ServerResponse> routes(ProductHandler handler) {
    return route(GET("/fn/products").and(accept(APPLICATION_JSON)), handler::all)
        .andRoute(POST("/fn/products").and(accept(APPLICATION_JSON)), handler::save)
        .andRoute(DELETE("/fn/products").and(accept(APPLICATION_JSON)), handler::deleteAll)
        // Order is important, if this pushed to last, event will never be called
        .andRoute(GET("/fn/products/events").and(accept(TEXT_EVENT_STREAM)), handler::events)
        .andRoute(GET("/fn/products/{id}").and(accept(APPLICATION_JSON)), handler::byId)
        .andRoute(PUT("/fn/products/{id}").and(accept(APPLICATION_JSON)), handler::update)
        .andRoute(DELETE("/fn/products/{id}").and(accept(APPLICATION_JSON)), handler::delete);

   /* return nest(path("/products"),
        nest(accept(APPLICATION_JSON).or(contentType(APPLICATION_JSON)).or(accept(TEXT_EVENT_STREAM)),
            route(GET("/"), handler::all)
                .andRoute(method(HttpMethod.POST), handler::save)
                .andRoute(DELETE("/"), handler::deleteAll)
                .andRoute(GET("/events"), handler::events)
                .andNest(path("/{id}"),
                    route(method(HttpMethod.GET), handler::byId)
                        .andRoute(method(HttpMethod.PUT), handler::update)
                        .andRoute(method(HttpMethod.DELETE), handler::delete)
                )
        )
    );*/
  }

}
