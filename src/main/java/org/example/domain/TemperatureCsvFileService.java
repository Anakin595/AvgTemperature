package org.example.domain;

import lombok.RequiredArgsConstructor;
import org.example.model.City;
import org.example.model.TemperatureEntry;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            return lines.map(TemperatureEntry::fromCsvLine)
                    .map(TemperatureEntry::getCity)
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
    public List<TemperatureResponseEntity> getYearlyAvgTemperatureForCity(final City city) throws IOException {
        Map<Integer, TemperatureResponseEntity> resultMap = new HashMap<>();
        try (Stream<String> lines = fileConfig.getFileStreamLines()) {
            lines.map(TemperatureEntry::fromCsvLine)
                    .filter(entry -> entry.getCity().equals(city))
                    .forEach(entry -> {
                        TemperatureResponseEntity entity = getResponseEntityByEntry(resultMap, entry);
                        entity.addTemperatureEntry(entry.getTemperatureValue());
                    });
        }

        List<TemperatureResponseEntity> resultList = resultMap.values().stream().toList();
        resultList.forEach(TemperatureResponseEntity::calculateAvgTemperature);
        return resultList;
    }

    private TemperatureResponseEntity getResponseEntityByEntry(final Map<Integer, TemperatureResponseEntity> map, final TemperatureEntry entry) {
        int year = entry.getDateTime().getYear();
        return map.computeIfAbsent(year, k -> new TemperatureResponseEntity(year));
    }


}
