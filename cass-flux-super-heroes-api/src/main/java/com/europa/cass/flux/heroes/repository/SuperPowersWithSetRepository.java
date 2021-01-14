package com.europa.cass.flux.heroes.repository;

import com.europa.cass.flux.heroes.dto.SuperPowerPrimaryKey;
import com.europa.cass.flux.heroes.dto.SuperPowersWithSet;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperPowersWithSetRepository extends
    ReactiveCassandraRepository<SuperPowersWithSet, SuperPowerPrimaryKey> {

}
