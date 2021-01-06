package com.europa.cass.flux.heroes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperPowersDomain {

  private long id;
  private String name;
  private String hero;
  private String powers;

}
