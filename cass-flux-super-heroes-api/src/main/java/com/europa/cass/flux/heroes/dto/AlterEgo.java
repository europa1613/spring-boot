package com.europa.cass.flux.heroes.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("alter_egos")
public class AlterEgo {

  @PrimaryKey
  private AlterEgoPrimaryKey key;

  @Column("updated_ts")
  private LocalDateTime updatedTs;

}
