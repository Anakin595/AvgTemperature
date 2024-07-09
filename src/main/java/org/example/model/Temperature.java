package org.example.model;

public record Temperature(Double value) {

    public static Temperature of(String text) {
        if (null == text || text.isBlank()) {
            return null;
        }

        double parsedValue;
        try {
            parsedValue = Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return null;
        }

        return new Temperature(parsedValue);
    }

}
