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
}
