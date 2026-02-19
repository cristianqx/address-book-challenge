package com.challenge.parser;

import com.challenge.domain.enumeration.Gender;
import com.challenge.domain.entity.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.stream.Stream;

public class PersonParser {

    private static final int EXPECTED_FIELDS = 3;
    private static final int NAME_INDEX = 0;
    private static final int GENDER_INDEX = 1;
    private static final int DOB_INDEX = 2;
    private static final String FIELD_SEPARATOR = ",";

    private final DateTimeFormatter dateFormatter;

    public PersonParser() {
        this.dateFormatter = new DateTimeFormatterBuilder()
                .appendValue(ChronoField.DAY_OF_MONTH, 2)
                .appendLiteral('/')
                .appendValue(ChronoField.MONTH_OF_YEAR, 2)
                .appendLiteral('/')
                .appendValueReduced(ChronoField.YEAR, 2, 2, 1900)
                .toFormatter();
    }

    public List<Person> parseFile(final Path filePath) throws IOException {
        try (Stream<String> lines = Files.lines(filePath)) {
            return lines.filter(line -> !line.isBlank())
                    .map(this::parseLine)
                    .toList();
        }
    }

    private Person parseLine(final String line) {
        String[] parts = line.split(FIELD_SEPARATOR, -1);
        if (parts.length != EXPECTED_FIELDS) {
            throw new ParsingException("Invalid format for line: " + line + ". Expected 3 fields, found " + parts.length);
        }

        try {
            String name = parts[NAME_INDEX].trim();
            Gender gender = Gender.fromString(parts[GENDER_INDEX]);
            LocalDate dob = LocalDate.parse(parts[DOB_INDEX].trim(), dateFormatter);
            return new Person(name, gender, dob);
        } catch (ParsingException e) {
            throw e;
        } catch (Exception e) {
            throw new ParsingException("Error parsing line: " + line + ". " + e.getMessage(), e);
        }
    }

    public List<Person> parseStream(final InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines()
                    .map(this::parseLine)
                    .toList();
        }
    }
}
