package com.europa.kafka.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

  private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

  private final KafkaTemplate<String, Person> kafkaTemplate;

  public KafkaController(KafkaTemplate<String, Person> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @PostMapping("/person")
  public ResponseEntity<HttpStatus> save(@RequestBody Person person) {
    kafkaTemplate.send("people", person);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @KafkaListener(topics = "people")
  public void readFromKafka(Person person) {
    logger.info("Reading from Kafka: {}", person);
  }

}
