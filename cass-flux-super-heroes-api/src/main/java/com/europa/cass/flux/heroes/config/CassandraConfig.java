package com.europa.cass.flux.heroes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

  @Value("${spring.data.cassandra.contact-points}")
  private String contactPoints;

  @Value("${spring.data.cassandra.port}")
  private int port;

  @Value("${spring.data.cassandra.datacenter}")
  private String datacenter;

  @Value("${spring.data.cassandra.keyspace-name}")
  private String keyspace;

  @Value("${spring.data.cassandra.base-packages}")
  private String basePackages;


  @Override
  protected String getKeyspaceName() {
    return keyspace;
  }

  @Override
  protected String getLocalDataCenter() {
    return datacenter;
  }

  @Override
  public String[] getEntityBasePackages() {
    return new String[]{basePackages};
  }

  @Override
  public SchemaAction getSchemaAction() {
    return SchemaAction.CREATE_IF_NOT_EXISTS;
  }

  @Override
  protected String getContactPoints() {
    return contactPoints;
  }

  @Override
  protected int getPort() {
    return port;
  }
}
