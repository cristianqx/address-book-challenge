package com.challenge.domain.entity;

import com.challenge.domain.enumeration.Gender;

import java.time.LocalDate;
import java.util.Objects;

public record Person(String name, Gender gender, LocalDate dateOfBirth) {
    public Person {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(gender, "Gender cannot be null");
        Objects.requireNonNull(dateOfBirth, "Date of birth cannot be null");

        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
    }
}
