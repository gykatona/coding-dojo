package com.dojo.codingdojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceProvider {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
