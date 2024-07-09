package domain;

import org.example.domain.CsvFileConfig;
import org.example.domain.TemperatureCsvFileService;
import org.example.domain.TemperatureResponseEntity;
import org.example.model.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class TemperatureCsvFileServiceTest {

    CsvFileConfig fileConfig;

    TemperatureCsvFileService temperatureCsvFileService;

    @BeforeEach
    void setup() throws IOException {
        Resource resource = new ClassPathResource("test_file.csv");
        Path filePath = Paths.get(resource.getURI());
        System.setProperty("file.csv.location", filePath.toString());

        fileConfig = new CsvFileConfig(filePath.toString());
        temperatureCsvFileService = new TemperatureCsvFileService(fileConfig);
    }

    @Test
    @DisplayName("Should fetch cities from CSV file")
    void fetchCities_successful() throws IOException {
        Set<City> result = temperatureCsvFileService.fetchCities();
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("Should calculate yearly average temperature for specific City ")
    void getAvgYearlyTemp_successful() throws IOException {
        List<TemperatureResponseEntity> result = temperatureCsvFileService.getYearlyAvgTemperatureForCity(City.of("Warszawa"));
        assertNotNull(result);
        assertEquals(3, result.size());
    }

}