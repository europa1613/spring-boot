package com.europa.cass.flux.heroes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlterEgoDomain {

  private long id;
  private String name;
  private String hero;

}
