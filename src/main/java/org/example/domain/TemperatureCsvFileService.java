package org.example.domain;

import lombok.RequiredArgsConstructor;
import org.example.model.City;
import org.example.model.TemperatureEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

@RequiredArgsConstructor
@Service
public class TemperatureCsvFileService implements TemperatureFileService {

    private final CsvFileConfig fileConfig;

    /**
     * Fetch all cities available for the file provided in application.yml
     *
     * @return Set of available cities.
     */
    @Override
    public Set<City> fetchCities() throws IOException {
        try (Stream<String> lines = fileConfig.getFileStreamLines()) {
            return lines.map(TemperatureEntity::fromCsvLine)
                    .map(TemperatureEntity::getCity)
                    .collect(Collectors.toSet());
        }
    }

    /**
     * Returns list of yearly average temperature for {City}
     *
     * @param city City
     * @return List of TemperatureResponseEntity
     */
    @Override
    public List<TemperatureResponseEntity> getYearlyAvgTemperatureForCity(City city) throws IOException {
        List<TemperatureResponseEntity> resultList = new ArrayList<>();

        try (Stream<String> lines = fileConfig.getFileStreamLines()) {
            lines.map(TemperatureEntity::fromCsvLine)
                    .filter(entity -> entity.getCity().equals(city)) // filter by City name
                    .collect(groupingBy(entity -> entity.getDateTime().getYear())) //group by year,
                    .forEach((key, value) -> { // for each year, calculate average temperature
                        TemperatureResponseEntity responseEntity = new TemperatureResponseEntity(key);
                        value.forEach(tempEntry -> responseEntity.addTemperatureEntry(tempEntry.getTemperatureValue()));
                        responseEntity.calculateAvgTemperature();
                        resultList.add(responseEntity);
                    });
        }

        return resultList;
    }
}
