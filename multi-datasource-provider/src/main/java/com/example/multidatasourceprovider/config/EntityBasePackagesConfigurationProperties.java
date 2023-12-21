package com.example.multidatasourceprovider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "multi-datasource.entities")
@Data
public class EntityBasePackagesConfigurationProperties {
    private List<String> mysqlPackage;
    private List<String> snowflakePackage;
}
