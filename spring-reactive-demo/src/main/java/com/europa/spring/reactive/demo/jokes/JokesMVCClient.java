package com.europa.spring.reactive.demo.jokes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

public class JokesMVCClient {

  private static final Logger logger = LoggerFactory.getLogger(JokesMVCClient.class);

  private static final String JOKES_API_URL = "http://localhost:3001/{count}/jokes?delay=2";

  private static final RestTemplate restTemplate = new RestTemplate();

  private static final ObjectMapper mapper = new ObjectMapper();

  static {
    restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(JOKES_API_URL));
  }

  public static void main(String[] args) throws JsonProcessingException {

    Instant start = Instant.now();
    for (int i = 1; i < 4; i++) {
      Joke[] jokes = restTemplate.getForObject(JOKES_API_URL, Joke[].class, 1);
      logger.info("====> Jokes: {} ", mapper.writeValueAsString(jokes));
    }
    logger.info("====> Elapsed time: {}ms", Duration.between(start, Instant.now()).toMillis());
  }

}
