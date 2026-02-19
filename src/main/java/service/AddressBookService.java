package com.challenge.service;

import com.challenge.domain.enumeration.Gender;
import com.challenge.domain.entity.Person;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AddressBookService {

    public long countMales(List<Person> people) {
        return people.stream()
                .filter(p -> p.gender() == Gender.MALE)
                .count();
    }

    public Optional<Person> findOldestPerson(List<Person> people) {
        return people.stream()
                .min(Comparator.comparing(Person::dateOfBirth));
    }

    public long daysBetween(List<Person> people, String firstName, String secondName) {
        Person first = findByExactName(people, firstName);
        Person second = findByExactName(people, secondName);

        return ChronoUnit.DAYS.between(first.dateOfBirth(), second.dateOfBirth());
    }

    private Person findByExactName(List<Person> people, String name) {
        List<Person> matches = people.stream()
                .filter(p -> p.name().equals(name))
                .toList();

        if (matches.isEmpty()) {
            throw new IllegalArgumentException("Person not found: " + name);
        }

        if (matches.size() > 1) {
            throw new IllegalStateException("Duplicate person name: " + name);
        }

        return matches.get(0);
    }
}
