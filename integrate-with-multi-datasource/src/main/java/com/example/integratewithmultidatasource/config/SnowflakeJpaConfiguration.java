package com.example.integratewithmultidatasource.config;

import com.example.integratewithmultidatasource.repository.snowflake.SnowflakeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
//    basePackages = "com.example.integratewithmultidatasource.repository.snowflake",
    basePackageClasses = SnowflakeRepository.class,
    entityManagerFactoryRef = "snowflakeEntityManagerFactory",
    transactionManagerRef = "snowflakeTransactionManager"
)
public class SnowflakeJpaConfiguration {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hibernateAuto;

    @Value("${spring.datasource.snowflake.dialect}")
    private String hibernateDialect;


    @Bean
    public LocalContainerEntityManagerFactoryBean snowflakeEntityManagerFactory(
        @Qualifier("snowflakeDataSource") DataSource dataSource) {
        var localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.example.integratewithmultidatasource.entity.product");
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(
            Map.of("hibernate.hbm2ddl.auto", hibernateAuto, "hibernate.dialect", hibernateDialect));
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    @Primary
    public PlatformTransactionManager snowflakeTransactionManager(
        @Qualifier("snowflakeEntityManagerFactory") LocalContainerEntityManagerFactoryBean snowflakeEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(snowflakeEntityManagerFactory.getObject()));
    }
}
