package org.example.controler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.TemperatureFileService;
import org.example.domain.TemperatureResponseEntity;
import org.example.model.City;
import org.example.utils.Timed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("temperature")
@AllArgsConstructor
@Slf4j
public class TemperatureController {

    private final TemperatureFileService temperatureFileService;

    @GetMapping("{city}")
    @Timed
    public ResponseEntity<List<TemperatureResponseEntity>> getYearlyAvgTemperature(
            @PathVariable("city") String city
    ) throws IOException {
        log.info("Calculating yearly avg temperature for {}", city);
        return ResponseEntity.ok(temperatureFileService.getYearlyAvgTemperatureForCity(City.of(city)));
    }

}
