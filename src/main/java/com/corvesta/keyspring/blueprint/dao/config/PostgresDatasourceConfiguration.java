package com.corvesta.keyspring.blueprint.dao.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

//This class can be used as is. The config needs to change for the right DB


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = {
		"com.corvesta.keyspring.blueprint.dao.postgres" })

public class PostgresDatasourceConfiguration {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Primary
	@Bean(name = "postgresDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource postgresDataSource() {
		logger.info("Inside postgresDataSource");
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("postgresDataSource") DataSource postgresDataSource) {
		logger.info("Inside postgresDataSource");
		return builder.dataSource(postgresDataSource).packages("com.corvesta.keyspring.blueprint.model.postgres").persistenceUnit("default").build();
	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		logger.info("Inside postgresDataSource");
		return new JpaTransactionManager(entityManagerFactory);
	}

}
