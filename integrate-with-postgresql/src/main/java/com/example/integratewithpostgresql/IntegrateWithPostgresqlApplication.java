package com.example.integratewithpostgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class IntegrateWithPostgresqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegrateWithPostgresqlApplication.class, args);
    }

}
