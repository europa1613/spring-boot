package com.europa.cass.flux.heroes;

import com.europa.cass.flux.heroes.data.loader.SuperPowersWithSetDataLoader;
import com.europa.cass.flux.heroes.domain.AlterEgoService;
import com.europa.cass.flux.heroes.domain.FluxSuperPowersService;
import com.europa.cass.flux.heroes.domain.SuperHeroService;
import com.europa.cass.flux.heroes.dto.SuperPowersWithSet;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
//@EnableAsync
public class CassFluxSuperHeroesApiApplication {

  static {
    BlockHound
        //.builder()
        // adding this startup works but blockhoudn doesn't dectect blocking call
        //.allowBlockingCallsInside("com.datastax.oss.driver.api.core.uuid.Uuids", "random")
        //.with(new SpringCoreBlockHoundIntegration())
        //.nonBlockingThreadPredicate(current -> current.or(it -> it.getName().contains("s0-admin-")))
        //.nonBlockingThreadPredicate(current -> current.or(it -> it.getName().contains("s0-io-")))
        //.addDynamicThreadPredicate(thread -> thread.getName().contains("s0-admin-"))
        //.addDynamicThreadPredicate(thread -> thread.getName().contains("s0-io-"))
        .install(builder -> { // Finally working :) Only logs the exception
          builder.blockingMethodCallback(it -> {
            new Exception(it.toString()).printStackTrace();
          });
        });

    /*
      @link https://projectreactor.io/docs/core/release/reference/#reactor-tools-debug
     *
     * ReactorDebugAgent.init();
     */

  }

  public static void main(String[] args) {
    SpringApplication.run(CassFluxSuperHeroesApiApplication.class, args);
  }

  @Value("${api.super.hero.base.uri}")
  private String superHeroUrl;

  @Value("${api.alter.ego.base.uri}")
  private String alterEgoUrl;

  @Value("${api.delay}")
  private int apiDelay;

  @Bean
  public SuperHeroService superHeroService() {
    return new SuperHeroService(superHeroUrl, apiDelay, WebClient.create(superHeroUrl));
  }

  @Bean
  public AlterEgoService alterEgoService() {
    return new AlterEgoService(alterEgoUrl, apiDelay, WebClient.create(alterEgoUrl));
  }


  @Value("${api.all.super.powers.base.uri}")
  private String superPowersUrl;


  @Bean
  public FluxSuperPowersService fluxSuperPowersService() {
    return new FluxSuperPowersService(superPowersUrl, apiDelay, WebClient.create(superPowersUrl));
  }

}

@RestController
@RequestMapping
@RequiredArgsConstructor
class PingPongController {

  private final SuperPowersWithSetDataLoader superPowersWithSetDataLoader;
  private final BlockingGreetingService greetingService;

  @GetMapping("/ping")
  public Mono<Map<String, String>> ping() throws InterruptedException {
    Thread.sleep(1000);
    return Mono.just(new HashMap<String, String>() {{
                       put("ping", "pong");
                     }}
    );
  }

  @GetMapping("/blocking-greeting")
  public Mono<String> greeting() {
    return Mono.just(greetingService.getGreeting());
  }

  // FAILS
  //works after importing/adding the server cert to java keystore - cacerts
  @GetMapping("/secure/resttemplate")
  public Mono<ResponseEntity<RandomNumber>> restTemplateWithoutSSLSetup() {
    RestTemplate template = new RestTemplate();

    RequestEntity<Void> request = RequestEntity.get(URI.create("https://localhost:8080/random/int"))
        .accept(MediaType.APPLICATION_JSON).build();
    ResponseEntity<RandomNumber> entity = template
        .exchange(request, RandomNumber.class);
    return Mono.just(entity);
  }

  @Value("${trust.store}")
  private Resource trustStore;

  @Value("${trust.store.password}")
  private String trustStorePassword;

  RestTemplate restTemplate() throws Exception {
    SSLContext sslContext = new SSLContextBuilder()
        .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
        .build();
    SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
    HttpClient httpClient = HttpClients.custom()
        .setSSLSocketFactory(socketFactory)
        .build();
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
        httpClient);
    return new RestTemplate(factory);
  }

  // FAILS due SAN not available
  // Works after certificate is added with SAN, Subject Alternative
  @GetMapping("/secure/ssl")
  public Mono<ResponseEntity<RandomNumber>> restTemplateWithSSL() throws Exception {
    RestTemplate template = restTemplate();
    ParameterizedTypeReference<HashMap<String, Integer>> responseType =
        new ParameterizedTypeReference<HashMap<String, Integer>>() {
        };
    RequestEntity<Void> request = RequestEntity.get(URI.create("https://localhost:8080/random/int"))
        .accept(MediaType.APPLICATION_JSON).build();
    ResponseEntity<RandomNumber> entity = template
        .exchange(request, RandomNumber.class);
    return Mono.just(entity);
  }

  //works after importing/adding the server cert to java keystore - cacerts

  // keytool -export -alias europa -keystore europa.jks -rfc -file europa_X509_certificate.cer
  //arvins-mac @ /Library/Java/JavaVirtualMachines/jdk1.8.0_271.jdk/Contents/Home/jre/lib/security
  // [22] → keytool -import -keystore cacerts -file ~/2-javaspace/spring-boot/springboot-https-demo/src/main/resources/keystore/europa.jks
  @GetMapping("/ssl/webclient")
  public Mono<RandomNumber> webclientSSL() {
    return WebClient.create("https://localhost:8080/random/int")
        .get()
        .retrieve()
        .bodyToMono(RandomNumber.class);
  }

  @GetMapping("/super-powers-w-set-type")
  public Flux<SuperPowersWithSet> superPowersWSet() {
    return superPowersWithSetDataLoader.load("1234XYZ");
  }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class RandomNumber {

  private Integer randomNumber;

}

@Service
@NoArgsConstructor
class BlockingGreetingService {

  public String getGreeting() {
    try {
      // Simulate blocking I/O call with a blocking sleep call
      Thread.sleep(50);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    }

    return "Hello, Mr. Stark!";
  }
}

