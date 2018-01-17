package com.corvesta.keyspring.blueprint.dao.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// This class can be used as is. The config needs to change for the right DB

@Configuration
public class OracleDatasourceConfiguration {

	@Bean(name = "oracleDataSource")
	@ConfigurationProperties(prefix = "oracle.datasource")
	public DataSource oracleDataSource() {
		return DataSourceBuilder.create().build();
	}

}
