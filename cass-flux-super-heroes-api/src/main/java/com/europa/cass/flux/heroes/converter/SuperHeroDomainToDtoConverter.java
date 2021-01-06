package com.europa.cass.flux.heroes.converter;

import com.europa.cass.flux.heroes.domain.SuperHeroDomain;
import com.europa.cass.flux.heroes.dto.SuperHero;
import com.europa.cass.flux.heroes.dto.SuperHeroPrimaryKey;
import java.time.LocalDateTime;

public class SuperHeroDomainToDtoConverter {

  public static SuperHero convert(SuperHeroDomain domain) {
    final SuperHeroPrimaryKey key = new SuperHeroPrimaryKey(domain.getId(), LocalDateTime.now());
    return new SuperHero(key, domain.getName());
  }

}
