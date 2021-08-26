package com.dojo.codingdojo.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
public class RemoteDataSourceConfiguration {
    @Bean
    @ConfigurationProperties("remote.datasource")
    public DataSourceProperties remoteDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public HikariDataSource remoteDataSource(@Qualifier("remoteDataSourceProperties") DataSourceProperties remoteDataSourceProperties) {
        return remoteDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public NamedParameterJdbcTemplate remoteNamedParameterJdbcTemplate(@Qualifier("remoteDataSource") DataSource remoteDataSource) {
        return new NamedParameterJdbcTemplate(remoteDataSource);
    }

    @Bean
    public PlatformTransactionManager remotePlatformTransactionManager(@Qualifier("remoteDataSource") DataSource remoteDataSource) {
        return new DataSourceTransactionManager(remoteDataSource);
    }

    @Bean
    public TransactionTemplate remoteTransactionTemplate(@Qualifier("remotePlatformTransactionManager") PlatformTransactionManager remotePlatformTransactionManager) {
        return new TransactionTemplate(remotePlatformTransactionManager);
    }
}
