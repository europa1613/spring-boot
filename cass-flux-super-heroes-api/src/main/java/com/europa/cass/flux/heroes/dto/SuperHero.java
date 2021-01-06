package com.europa.cass.flux.heroes.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("heroes")
public class SuperHero {

  @PrimaryKey
  private SuperHeroPrimaryKey key;

  @Column
  private String name;

}
