package com.example.integratewithmultidatasource.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowflakeDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.snowflake")
    public DataSourceProperties snowflakeDataSourceProperties() {
        return new DataSourceProperties();
    }
}
