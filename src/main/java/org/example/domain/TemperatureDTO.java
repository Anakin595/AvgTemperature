package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.example.utils.DecimalSerializer;

@Data
public class TemperatureDTO {

    private final int year;
    @JsonSerialize(using = DecimalSerializer.class)
    @Getter
    private Double averageTemperature;

    @JsonIgnore
    @Getter(AccessLevel.PRIVATE)
    private int temperatureEntriesNumber;
    @JsonIgnore
    @Getter(AccessLevel.PRIVATE)
    private double cumulativeTemperature;

    TemperatureDTO(int year) {
        this(year, null);
    }

    TemperatureDTO(int year, Double initialAvgTemperature) {
        this.year = year;
        averageTemperature = initialAvgTemperature;
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
