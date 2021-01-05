package com.europa.kafka.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

  private final KafkaTemplate<String, Person> kafkaTemplate;

  public KafkaController(KafkaTemplate<String, Person> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @PostMapping("/person")
  public ResponseEntity<HttpStatus> save(@RequestBody Person person) {
    kafkaTemplate.send("people", person);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
