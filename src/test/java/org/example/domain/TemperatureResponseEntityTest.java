package org.example.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureResponseEntityTest {

    private TemperatureResponseEntity tempEntity;

    private final int TEST_YEAR = 2024;
    private final double TEST_TEMP_ENTRY_VALUE = 10.0;

    @BeforeEach
    void setup() {
        tempEntity = new TemperatureResponseEntity(TEST_YEAR);
    }

    @Test
    @DisplayName("Accessing avgTemp value returns value only when calculateAvgTemperature called.")
    void should_calculate_only_when_called() {
        //given
        //when
        tempEntity.addTemperatureEntry(TEST_TEMP_ENTRY_VALUE);
        //then
        assertEquals(TEST_YEAR, tempEntity.getYear());
        assertNull(tempEntity.getAverageTemperature());

        //when
        tempEntity.calculateAvgTemperature();
        //then
        assertNotNull(tempEntity.getAverageTemperature());
        assertEquals(TEST_TEMP_ENTRY_VALUE, tempEntity.getAverageTemperature());
    }

    @Test
    @DisplayName("Should calculate avg properly")
    void should_calculate_avgTemp() {
        //given
        double[] temperatureTable = new double[]{1.0, 2.0, 3.0};
        double incorrectAvg = 0.0;
        double avg = Arrays.stream(temperatureTable).average().orElse(incorrectAvg);

        //when
        Arrays.stream(temperatureTable).forEach(t -> tempEntity.addTemperatureEntry(t));
        tempEntity.calculateAvgTemperature();
        assertEquals(TEST_YEAR, tempEntity.getYear());

        //then
        assertEquals(avg, tempEntity.getAverageTemperature());
    }

}