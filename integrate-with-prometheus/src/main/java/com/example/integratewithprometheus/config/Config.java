package com.example.integratewithprometheus.config;

import com.example.integratewithprometheus.service.SampleService;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class Config {

    private final SampleService sampleService;


    @Bean
    public Counter firstCounter(MeterRegistry meterRegistry){
       return Counter.builder("example_counter_test")
            .description("It's a description test for counter")
            .baseUnit("SECONDS")
           .tag("application", "test")
            .register(meterRegistry);
    }

    @Bean
    public Counter secondCounter(MeterRegistry meterRegistry){
        return Counter.builder("example_counter_test")
            .description("It's a description test for second counter")
            .baseUnit("SECONDS")
            .tag("application", "dev")
            .register(meterRegistry);
    }

    @Bean
    public Gauge gauge(MeterRegistry meterRegistry){
        return Gauge.builder("example_gauge_test", sampleService::getNumber)
            .description("It's a description test")
            .tag("tag1", "narudo")
            .register(meterRegistry);
    }

    @Bean
    public Gauge secondGauge(MeterRegistry meterRegistry){
        return Gauge.builder("example_gauge_test", sampleService::getNumber)
            .description("It's a description test")
            .tag("tag1", "sakura")
            .register(meterRegistry);
    }

    @Bean
    Timer timer(MeterRegistry meterRegistry){
        return Timer.builder("test.timer")
//            .publishPercentileHistogram()
            .serviceLevelObjectives(Duration.ofMillis(1000), Duration.ofMillis(2000), Duration.ofMillis(3000))
            .register(meterRegistry);
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry meterRegistry) {
        return new TimedAspect(meterRegistry);
    }

    @Bean
    public DistributionSummary distributionSummary(MeterRegistry meterRegistry) {
        return DistributionSummary.builder("custom.distribution.summary")
            .scale(1000)
            .serviceLevelObjectives(100, 200, 500, 800, 1000)
            .register(meterRegistry);
    }
}
