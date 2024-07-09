package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.example.utils.DecimalSerializer;

@Data
public class TemperatureResponseEntity {

    private final int year;
    @JsonSerialize(using = DecimalSerializer.class)
    private Double averageTemperature;

    @JsonIgnore
    private int temperatureEntriesNumber;
    @JsonIgnore
    private double cumulativeTemperature;

    TemperatureResponseEntity(int year) {
        this.year = year;
        averageTemperature = null;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void calculateAvgTemperature() {
        averageTemperature = cumulativeTemperature / temperatureEntriesNumber;
    }

    public void addTemperatureEntry(double temperatureEntry) {
        cumulativeTemperature += temperatureEntry;
        temperatureEntriesNumber++;
    }

    @Override
    public String toString() {
        return year + ": " + averageTemperature;
    }


}
