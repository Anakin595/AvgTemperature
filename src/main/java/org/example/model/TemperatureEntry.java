package org.example.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Setter(AccessLevel.PRIVATE)
public class TemperatureEntry {

    private final City city;
    private final LocalDateTime dateTime;
    private final Temperature temperature;

    TemperatureEntry(String city, LocalDateTime dateTime, String temp) {
        this.city = City.of(city);
        this.dateTime = dateTime;
        this.temperature = Temperature.of(temp);
    }

    public static TemperatureEntry fromCsvLine(String csvLine) {
        String[] parts = csvLine.split(";");
        String cityString = parts[0];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");//2021-05-22 05:17:28.769
        LocalDateTime dateTime = LocalDateTime.parse(parts[1], formatter);
        String tempDouble = parts[2];
        return new TemperatureEntry(cityString, dateTime, tempDouble);
    }

    public double getTemperatureValue() {
        return this.temperature.value();
    }

}
