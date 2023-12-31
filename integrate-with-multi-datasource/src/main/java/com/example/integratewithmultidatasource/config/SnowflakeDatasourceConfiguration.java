package com.example.integratewithmultidatasource.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class SnowflakeDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.snowflake")
    public DataSourceProperties snowflakeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource snowflakeDataSource() {
        return snowflakeDataSourceProperties()
            .initializeDataSourceBuilder()
            .build();
    }
}
