package com.europa.cass.flux.heroes.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlterEgoPrimaryKey {

  @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
  private long id;

  @PrimaryKeyColumn(name = "name", type = PrimaryKeyType.CLUSTERED, ordinal = 1, ordering = Ordering.ASCENDING)
  private String name;

  @PrimaryKeyColumn(name = "hero", type = PrimaryKeyType.CLUSTERED, ordinal = 2, ordering = Ordering.DESCENDING)
  private String hero;

}
