package com.challenge.service;

import com.challenge.domain.entity.Person;
import com.challenge.domain.enumeration.Gender;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.List;

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

}
