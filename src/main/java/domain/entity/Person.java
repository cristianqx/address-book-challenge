package com.challenge.domain.entity;

import com.challenge.domain.enumeration.Gender;

import java.time.LocalDate;
import java.util.Objects;

public record Person(String name, Gender gender, LocalDate dateOfBirth) { }
