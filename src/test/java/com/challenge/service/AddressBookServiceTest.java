package com.challenge.service;

import com.challenge.domain.enumeration.Gender;
import com.challenge.domain.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookServiceTest {

    private AddressBookService service;
    private List<Person> people;

    @BeforeEach
    void setUp() {
        service = new AddressBookService();
        people = List.of(
                new Person("Bill McKnight", Gender.MALE, LocalDate.of(1977, 3, 16)),
                new Person("Paul Robinson", Gender.MALE, LocalDate.of(1985, 1, 15)),
                new Person("Gemma Lane", Gender.FEMALE, LocalDate.of(1991, 11, 20)),
                new Person("Sarah Jane", Gender.FEMALE, LocalDate.of(1980, 7, 12)),
                new Person("Ian Wright", Gender.MALE, LocalDate.of(1974, 8, 14))
        );
    }

    @Test
    void shouldCountMales() {
        assertEquals(3, service.countMales(people));
    }

    @Test
    void shouldFindOldestPerson() {
        final Person oldest = service.findOldestPerson(people).orElseThrow();
        assertEquals("Ian Wright", oldest.name());
    }

    @Test
    void shouldCalculateDaysBetweenBillAndPaul() {
        final LocalDate billDob = LocalDate.of(1977, 3, 16);
        final LocalDate paulDob = LocalDate.of(1985, 1, 15);
        final long expected = ChronoUnit.DAYS.between(billDob, paulDob);

        final long days = service.daysBetween(people, "Bill McKnight", "Paul Robinson");
        assertEquals(expected, days);
    }

    @Test
    void shouldThrowWhenPersonNotFound() {
        assertThrows(IllegalArgumentException.class, () -> service.daysBetween(people, "Unknown", "Paul Robinson"));
    }

    @Test
    void shouldThrowWhenDuplicateName() {
        final List<Person> duplicated = List.of(
                new Person("Bill McKnight", Gender.MALE, LocalDate.of(1977, 3, 16)),
                new Person("Bill McKnight", Gender.MALE, LocalDate.of(1977, 3, 16)),
                new Person("Paul Robinson", Gender.MALE, LocalDate.of(1985, 1, 15))
        );

        assertThrows(IllegalStateException.class, () -> service.daysBetween(duplicated, "Bill McKnight", "Paul Robinson"));
    }

    @Test
    void shouldReturnEmptyWhenFindingOldestFromEmptyList() {
        final Optional<Person> result = service.findOldestPerson(List.of());
        assertTrue(result.isEmpty());
    }
}
