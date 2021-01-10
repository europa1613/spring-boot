package com.europa.springboot.https.demo;

import java.net.URI;
import javax.net.ssl.SSLContext;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootHttpsDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("ssl")
public class HttpsApplicationIntegrationTest {

  private static final String WELCOME_URL = "https://localhost:8080/random/int";

  @Value("${trust.store}")
  private Resource trustStore;

  @Value("${trust.store.password}")
  private String trustStorePassword;

  @Test
  @SuppressWarnings("unchecked")
  public void whenGETanHTTPSResource_thenCorrectResponse() throws Exception {
    RestTemplate template = restTemplate();

    RequestEntity<Void> request = RequestEntity.get(URI.create(WELCOME_URL))
        .accept(MediaType.APPLICATION_JSON).build();
    ResponseEntity<RandomNumber> object = template
        .exchange(request, RandomNumber.class);

    System.out.println(object);

    //assertEquals("<h1>Welcome to Secured Site</h1>", response.getBody());
    //assertEquals(HttpStatus.OK, response.getStatusCode());
  }

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
}