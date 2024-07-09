package org.example.controler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.TemperatureFileService;
import org.example.model.City;
import org.example.utils.Timed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("city")
@AllArgsConstructor
@Slf4j
public class CityController {

    private final TemperatureFileService temperatureFileService;

    @GetMapping
    @Timed
    public ResponseEntity<Set<City>> getAvailableCities() throws IOException {
        log.info("Fetching cities...");
        return ResponseEntity.ok(temperatureFileService.fetchCities());
    }

}
