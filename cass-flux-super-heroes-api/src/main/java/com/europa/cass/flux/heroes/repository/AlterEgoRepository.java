package com.europa.cass.flux.heroes.repository;

import com.europa.cass.flux.heroes.dto.AlterEgo;
import com.europa.cass.flux.heroes.dto.AlterEgoPrimaryKey;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlterEgoRepository extends
    ReactiveCassandraRepository<AlterEgo, AlterEgoPrimaryKey> {

}
