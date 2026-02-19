package com.challenge.service;

import com.challenge.domain.enumeration.Gender;
import com.challenge.domain.entity.Person;

import java.util.List;

public class AddressBookService {

    public long countMales(List<Person> people) {
        return people.stream()
                .filter(p -> p.gender() == Gender.MALE)
                .count();
    }

    public long daysBetween(List<Person> people, String firstName, String secondName) {
        Person first = findByExactName(people   , firstName);
        Person second = findByExactName(people, secondName);

        return ChronoUnit.DAYS.between(first.dateOfBirth(), second.dateOfBirth());
    }
}
