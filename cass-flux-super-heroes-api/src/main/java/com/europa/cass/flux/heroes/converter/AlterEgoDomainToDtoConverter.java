package com.europa.cass.flux.heroes.converter;

import com.europa.cass.flux.heroes.domain.AlterEgoDomain;
import com.europa.cass.flux.heroes.dto.AlterEgo;
import com.europa.cass.flux.heroes.dto.AlterEgoPrimaryKey;
import java.time.LocalDateTime;

public class AlterEgoDomainToDtoConverter {

  public static AlterEgo convert(AlterEgoDomain domain) {
    final AlterEgoPrimaryKey key = new AlterEgoPrimaryKey(domain.getId(), domain.getName(),
        domain.getHero());
    return new AlterEgo(key, LocalDateTime.now());
  }

}
