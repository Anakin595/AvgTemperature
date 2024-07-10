package org.example.domain;

import org.example.model.City;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface TemperatureFileService {

    Set<City> fetchCities() throws IOException;

    List<TemperatureDTO> getYearlyAvgTemperatureForCity(City city) throws IOException;

}
