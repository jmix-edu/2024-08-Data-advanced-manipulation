package com.company.jmixpm;

import io.jmix.autoconfigure.data.JmixLiquibaseCreator;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import io.jmix.core.JmixModules;
import io.jmix.core.Resources;
import io.jmix.data.impl.JmixEntityManagerFactoryBean;
import io.jmix.data.impl.JmixTransactionManager;
import io.jmix.data.persistence.DbmsSpecifics;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;

@Configuration
public class CustomersStoreConfiguration {

    @Bean
    @ConfigurationProperties("customers.datasource")
    DataSourceProperties customersDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "customers.datasource.hikari")
    DataSource customersDataSource(@Qualifier("customersDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean customersEntityManagerFactory(
            @Qualifier("customersDataSource") DataSource dataSource,
            JpaVendorAdapter jpaVendorAdapter,
            DbmsSpecifics dbmsSpecifics,
            JmixModules jmixModules,
            Resources resources
    ) {
        return new JmixEntityManagerFactoryBean("customers", dataSource, jpaVendorAdapter, dbmsSpecifics, jmixModules, resources);
    }

    @Bean
    JpaTransactionManager customersTransactionManager(@Qualifier("customersEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JmixTransactionManager("customers", entityManagerFactory);
    }

    @Bean("customersLiquibaseProperties")
    @ConfigurationProperties(prefix = "customers.liquibase")
    public LiquibaseProperties customersLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean("customersLiquibase")
    public SpringLiquibase customersLiquibase(@Qualifier("customersDataSource") DataSource dataSource,
                                              @Qualifier("customersLiquibaseProperties") LiquibaseProperties liquibaseProperties) {
        return JmixLiquibaseCreator.create(dataSource, liquibaseProperties);
    }
}
