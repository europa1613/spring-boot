package com.europa.cass.flux.heroes.converter;

import com.europa.cass.flux.heroes.domain.SuperPowersDomain;
import com.europa.cass.flux.heroes.dto.SuperPowerPrimaryKey;
import com.europa.cass.flux.heroes.dto.SuperPowersWithSet;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SuperPowersDomainToDTOWithSetConverter {

  public static SuperPowersWithSet convert(SuperPowersDomain domain) {
    final SuperPowerPrimaryKey key = new SuperPowerPrimaryKey(domain.getId(), domain.getName(),
        domain.getHero());
    String[] powers = domain.getPowers().split(",");
    Set<String> set = new HashSet<>(Arrays.asList(powers));
    return new SuperPowersWithSet(key, set, LocalDateTime.now());
  }

}
