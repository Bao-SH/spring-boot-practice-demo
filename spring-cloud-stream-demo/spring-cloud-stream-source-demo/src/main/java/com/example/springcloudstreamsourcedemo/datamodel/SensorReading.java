package com.example.springcloudstreamsourcedemo.datamodel;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class SensorReading {
    private Timestamp timestamp;
    private String sensorID;
    private Double temperature;
    private BaseUnit baseUnit;

    public enum BaseUnit {
        CELSIUS,
        FAHRENHEIT
    }

    public SensorReading() {
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    public SensorReading(String sensorID, double temperature, BaseUnit baseUnit) {
        this();
        this.sensorID = sensorID;
        this.temperature = temperature;
        this.setBaseUnit(baseUnit);
    }
}
