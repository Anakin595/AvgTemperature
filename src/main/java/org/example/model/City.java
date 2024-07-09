package org.example.model;

public record City(String name) {

    public static City of(String city) {
        if (city == null || city.isBlank()) {
            return null;
        }

        return new City(city);
    }

}


