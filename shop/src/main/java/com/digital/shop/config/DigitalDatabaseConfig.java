package com.digital.shop.config;


import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@ConditionalOnProperty(value = "app.datasource.digital.enable", havingValue = "true", matchIfMissing = true)
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "digitalEntityManagerFactory",
        transactionManagerRef = "digitalTransactionManager", basePackages = {"com.digital.shop.repository"})
public class DigitalDatabaseConfig {

    @Value("${app.datasource.digital.url:#{null}}")
    private String urlForLog;

    @Primary
    @Bean(name = "digitalDataSourceProperties")
    @ConfigurationProperties("app.datasource.digital")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "digitalDataSource")
    @ConfigurationProperties(prefix = "app.datasource.digital.configuration")
    public DataSource dataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "digitalPropertiesHibernate")
    @ConfigurationProperties(prefix = "app.datasource.digital.properties")
    public Map<String, String> dataProperties() {
        return new HashMap<>();
    }

    @Primary
    @Bean(name = "digitalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        log.info("DB config digitalDataSource: " + urlForLog);
        return builder.dataSource(dataSource()).properties(dataProperties()).packages("com.digital.order.model.entity")
                .build();
    }

    @Primary
    @Bean(name = "digitalTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("digitalEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
