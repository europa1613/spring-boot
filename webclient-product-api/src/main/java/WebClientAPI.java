import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebClientAPI {

  private final WebClient client;

  public WebClientAPI() {
    //this.client = WebClient.create("http://localhost:8081/products");
    this.client = WebClient.builder()
        .baseUrl("http://localhost:8081/products")
        .build();
  }

  private Mono<ResponseEntity<Product>> save() {
    return client
        .post()
        .body(Mono.just(new Product(null, "Blue Tea", 10.99)), Product.class)
        .exchange()
        .flatMap(clientResponse -> clientResponse.toEntity(Product.class))
        .doOnSuccess(
            productResponseEntity -> System.out.println("***** POST: " + productResponseEntity));
  }

  private Flux<Product> all() {
    return client
        .get()
        .retrieve()
        .bodyToFlux(Product.class)
        .doOnNext(o -> System.out.println("******* GET ALL: " + o));
  }

  private Mono<Product> update(String id, String name, double price) {
    return client
        .put()
        .uri("/{id}", id)
        .body(Mono.just(new Product(null, name, price)), Product.class)
        .retrieve()
        .bodyToMono(Product.class)
        .doOnSuccess(o -> System.out.println("******* PUT: " + o));
  }

  private Mono<Void> delete(String id) {
    return client
        .delete()
        .uri("/{id}", id)
        .retrieve()
        .bodyToMono(Void.class)
        .doOnSuccess(o -> System.out.println("******* DELETE: " + o));
  }

  private Mono<Product> byId(String id) {
    return client
        .get()
        .uri("/{id}", id)
        .retrieve()
        .bodyToMono(Product.class)
        .doOnSuccess(o -> System.out.println("******* GET byId: " + o));
  }

  private Flux<ProductEvent> events() {
    return client
        .get()
        .uri("/events")
        .retrieve()
        .bodyToFlux(ProductEvent.class)
        .limitRate(5);
  }

  public static void main(String[] args) {
    WebClientAPI api = new WebClientAPI();

    api.save()
        .thenMany(api.all())
        .take(1)
        .flatMap(product -> api.update(product.getId(), "White Tea", 0.99))
        .flatMap(product -> api.delete(product.getId()))
        .thenMany(api.all())
        .thenMany(api.events())
        .subscribe(System.out::println);

    /*api.all()
        .subscribe(product -> System.out.println("############ ALL: " + product));*/

    /*api.client
        .get()
        .retrieve()
        .bodyToFlux(Product.class)
        //.doOnNext(o -> System.out.println("******* GET ALL: " + o))
        .subscribe(product -> System.out.println("############ ALL: " + product));*/

  }
}
