package com.europa.springboot.https.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringbootHttpsDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootHttpsDemoApplication.class, args);
  }

}

@RestController
@RequestMapping("/random")
class SimpleController {

  @GetMapping("/int")
  public Map<String, Integer> getRandom() {
    final Random random = new Random();
    final int i = random.ints(1, 99).findFirst().getAsInt();

    return new HashMap<String, Integer>() {{
      put("randomNumber", i);
    }};
  }
}
