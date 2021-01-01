package com.europa.spring.reactive.demo.jokes;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Joke {

  private String id;
  private String joke;
  private List<String> categories;

}
