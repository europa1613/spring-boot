package com.europa.cass.flux.heroes.dto;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("super_powers_with_set")
public class SuperPowersWithSet {

  @PrimaryKey
  private SuperPowerPrimaryKey key;

  @Column("powers")
  private Set<String> powers;

  @Column("updated_ts")
  private LocalDateTime updatedTs;

}
