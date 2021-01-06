package com.europa.cass.flux.heroes.dto;

import java.time.LocalDateTime;
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
public class SuperHeroPrimaryKey {

  @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
  private long id;

  @PrimaryKeyColumn(name = "updated_ts", type = PrimaryKeyType.CLUSTERED, ordinal = 0, ordering = Ordering.DESCENDING)
  private LocalDateTime updatedTs;

}
