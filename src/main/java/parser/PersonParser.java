package com.challenge.parser;

import com.challenge.domain.enumeration.Gender;
import com.challenge.domain.entity.Person;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

public class PersonParser {

    private final DateTimeFormatter dateFormatter;

    public PersonParser() {
        this.dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    }

    public List<Person> parseFile(Path filePath) throws IOException {
        try (Stream<String> lines = Files.lines(filePath)) {
            return lines
                    .map(this::parseLine)
                    .toList();
        }
    }

    public List<Person> parseStream(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .map(this::parseLine)
                .toList();
    }

    private Person parseLine(String line) {
        String[] parts = line.split(",");
        String name = parts[0];
        Gender gender = Gender.fromString(parts[1]);
        LocalDate dob = LocalDate.parse(parts[2], dateFormatter);

        return new Person(name, gender, dob);
    }
}