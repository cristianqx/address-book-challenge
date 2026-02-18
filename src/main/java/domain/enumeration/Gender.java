package com.challenge.domain.enumeration;

import java.util.Arrays;

public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    public static Gender fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Gender value cannot be null");
        }

        String trimmedValue = value.trim();

        return Arrays.stream(values())
                .filter(gender -> gender.label.equalsIgnoreCase(trimmedValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown gender: " + trimmedValue));
    }

    public String getLabel() {
        return label;
    }
}

