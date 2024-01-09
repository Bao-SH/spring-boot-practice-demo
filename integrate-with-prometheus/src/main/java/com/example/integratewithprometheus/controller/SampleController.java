package com.example.integratewithprometheus.controller;

import com.example.integratewithprometheus.service.SampleService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Timer;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @Resource(name = "firstCounter")
    private final Counter firstCounter;

    @Resource(name = "secondCounter")
    private final Counter secondCounter;

    @Resource(name = "gauge")
    private final Gauge gauge;

    @Resource(name = "secondGauge")
    private final Gauge secondGauge;

    private final Timer timer;

    private final DistributionSummary distributionSummary;

    @GetMapping("/")
    public String test() {
        firstCounter.increment(10);
        return "succeed";
    }

    @GetMapping("/second")
    public String secondTest() {
        secondCounter.increment(5);
        return "succeed";
    }

    @GetMapping("/timer")
    @Timed(value = "http.request.test.timer", histogram = false, description = "A timer description")
    public String timer() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(new Random().nextInt(5000));
        var end = System.currentTimeMillis();
        timer.record(end - start, TimeUnit.MILLISECONDS);
        System.out.println(end - start);
        return "succeed";
    }

    @GetMapping("/gauge")
    public String gauge() {
        return String.valueOf(gauge.value());
    }

    @GetMapping("/notes")
    public String notes() {
        distributionSummary.record(0.5);
        distributionSummary.record(0.7);
        distributionSummary.record(0.9);
        distributionSummary.record(0.2);
        return "succeed";
    }
}
