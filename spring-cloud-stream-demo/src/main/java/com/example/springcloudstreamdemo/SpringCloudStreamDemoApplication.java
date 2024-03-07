package com.example.springcloudstreamdemo;

import com.example.springcloudstreamdemo.datamodel.SensorReading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class SpringCloudStreamDemoApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringCloudStreamDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamDemoApplication.class, args);
    }

    @Bean
    public Function<SensorReading, SensorReading> convertFtoC() {
        return reading -> {
            log.info("Received: " + reading);

            double temperatureCelsius = (reading.getTemperature() - 32) * 5 / 9;
            reading.setTemperature(temperatureCelsius);
            reading.setBaseUnit(SensorReading.BaseUnit.CELSIUS);

            log.info("Sending: " + reading);

            return reading;
        };
    }
}
