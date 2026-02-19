package com.challenge.parser;

import com.challenge.domain.entity.Person;
import com.challenge.domain.enumeration.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonParserTest {

    private PersonParser parser;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        parser = new PersonParser();
    }

    @Test
    void shouldParseValidFileWithTwoDigitYear() throws IOException {
        Path file = tempDir.resolve("valid.txt");
        Files.write(file, List.of(
                "John Smith, Male, 17/03/77",
                "Jane Doe, Female, 05/11/85"
        ));

        List<Person> people = parser.parseFile(file);

        assertEquals(2, people.size());
        Person john = people.get(0);
        assertEquals("John Smith", john.name());
        assertEquals(Gender.MALE, john.gender());
        assertEquals(LocalDate.of(1977, 3, 17), john.dateOfBirth());

        Person jane = people.get(1);
        assertEquals("Jane Doe", jane.name());
        assertEquals(Gender.FEMALE, jane.gender());
        assertEquals(LocalDate.of(1985, 11, 5), jane.dateOfBirth());
    }

    @Test
    void shouldFailWhenInvalidFormat() throws IOException {
        Path file = tempDir.resolve("invalid_format.txt");
        Files.write(file, List.of("Invalid Line"));

        assertThrows(ParsingException.class, () -> parser.parseFile(file));
    }

    @Test
    void shouldFailWhenInvalidGender() throws IOException {
        Path file = tempDir.resolve("invalid_gender.txt");
        Files.write(file, List.of("John, Other, 17/03/77"));

        assertThrows(ParsingException.class, () -> parser.parseFile(file));
    }

    @Test
    void shouldFailWhenInvalidDate() throws IOException {
        Path file = tempDir.resolve("invalid_date.txt");
        Files.write(file, List.of("John, Male, 32/03/77"));

        assertThrows(ParsingException.class, () -> parser.parseFile(file));
    }

    @Test
    void shouldAcceptTwoDigitYearAndInterpretAsFullYear() throws IOException {
        Path file = tempDir.resolve("two_digit_year.txt");
        Files.write(file, List.of("Old Person, Male, 01/01/05"));

        List<Person> people = parser.parseFile(file);
        assertEquals(1, people.size());
        assertEquals(LocalDate.of(1905, 1, 1), people.get(0).dateOfBirth());
    }

    @Test
    void shouldFailFastOnInvalidLineInMiddle() throws IOException {
        Path file = tempDir.resolve("invalid_middle.txt");
        Files.write(file, List.of(
                "John Smith, Male, 17/03/77",
                "Invalid Line",
                "Jane Doe, Female, 05/11/85"
        ));

        ParsingException ex = assertThrows(ParsingException.class, () -> parser.parseFile(file));
        assertTrue(ex.getMessage().contains("Invalid Line") || ex.getMessage().contains("Expected 3 fields"));
    }

    @Test
    void shouldTrimExtraSpaces() throws IOException {
        Path file = tempDir.resolve("extra_spaces.txt");
        Files.write(file, List.of("  John Smith ,  Male , 17/03/77  "));

        List<Person> people = parser.parseFile(file);
        assertEquals("John Smith", people.get(0).name());
        assertEquals(Gender.MALE, people.get(0).gender());
        assertEquals(LocalDate.of(1977, 3, 17), people.get(0).dateOfBirth());
    }

    @Test
    void shouldReturnEmptyListForEmptyFile() throws IOException {
        Path file = tempDir.resolve("empty.txt");
        Files.createFile(file);

        List<Person> people = parser.parseFile(file);
        assertTrue(people.isEmpty());
    }
}
