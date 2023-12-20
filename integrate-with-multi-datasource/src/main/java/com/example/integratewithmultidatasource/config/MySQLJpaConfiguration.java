package com.example.integratewithmultidatasource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.example.integratewithmultidatasource.repository",
    entityManagerFactoryRef = "mysqlEntityManagerFactory",
    transactionManagerRef = "mysqlTransactionManager"
)
public class MySQLJpaConfiguration {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hibernateAuto;

    @Bean
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
        @Qualifier("mysqlDataSource") DataSource dataSource,
        EntityManagerFactoryBuilder builder) {
        var build = builder
            .dataSource(dataSource)
            .packages("com.example.integratewithmultidatasource.entity")
            .build();
        build.setJpaPropertyMap(Map.of("hibernate.hbm2ddl.auto", hibernateAuto));
        return build;
    }

    @Bean
    @Primary
    public PlatformTransactionManager mysqlTransactionManager(
        @Qualifier("mysqlEntityManagerFactory") LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(mysqlEntityManagerFactory.getObject()));
    }
}
