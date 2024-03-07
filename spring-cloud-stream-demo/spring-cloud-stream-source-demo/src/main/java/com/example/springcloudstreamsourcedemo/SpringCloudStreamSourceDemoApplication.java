package com.example.springcloudstreamsourcedemo;

import com.example.springcloudstreamsourcedemo.datamodel.SensorReading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

@SpringBootApplication
public class SpringCloudStreamSourceDemoApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringCloudStreamSourceDemoApplication.class);
	private static final Random random = new Random(System.currentTimeMillis());
	private static final UUID sensorIdentifier = UUID.randomUUID();
	private static final int RANDOM_MULTIPLIER = 100;


	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamSourceDemoApplication.class, args);
	}

	@Bean
	public Supplier<SensorReading> emitSensorReading() {
		return () -> {
			double temperature = random.nextDouble() * RANDOM_MULTIPLIER;

			SensorReading reading = new SensorReading();
			reading.setSensorID(sensorIdentifier.toString());
			reading.setTemperature(temperature);
			reading.setBaseUnit(SensorReading.BaseUnit.FAHRENHEIT);

			log.info("Emitting " + reading);

			return reading;
		};
	}

}
