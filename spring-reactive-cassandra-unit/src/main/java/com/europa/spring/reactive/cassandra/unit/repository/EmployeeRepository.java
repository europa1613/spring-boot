package com.europa.spring.reactive.cassandra.unit.repository;

import com.europa.spring.reactive.cassandra.unit.model.Employee;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveCassandraRepository<Employee, Integer> {
    @AllowFiltering
    Flux<Employee> findByAgeGreaterThan(int age);
}
