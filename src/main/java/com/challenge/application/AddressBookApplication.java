package com.challenge.application;

import com.challenge.domain.entity.Person;
import com.challenge.parser.PersonParser;
import com.challenge.service.AddressBookService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class AddressBookApplication {

    private static final String DEFAULT_RESOURCE = "AddressBook.txt";
    private static final String BILL = "Bill McKnight";
    private static final String PAUL = "Paul Robinson";

    private final PersonParser parser = new PersonParser();
    private final AddressBookService service = new AddressBookService();

    public void run(String[] args) throws IOException {
        final List<Person> people = resolvePeople(args);
        printResults(people);
    }


    private List<Person> resolvePeople(String[] args) throws IOException {
        if (args != null && args.length > 0) {
            Path path = Paths.get(args[0]);
            return parser.parseFile(path);
        }

        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(DEFAULT_RESOURCE);

        if (inputStream == null) {
            throw new IllegalStateException("Usage: java -jar ... <file-path> or place " + DEFAULT_RESOURCE + " in classpath.");
        }

        return parser.parseStream(inputStream);
    }

    private void printResults(List<Person> people) {
        System.out.println("Number of males: " + service.countMales(people));
        final Optional<Person> oldest = service.findOldestPerson(people);
        System.out.println("Oldest person: " + oldest.map(Person::name).orElse("N/A"));
        final long days = service.daysBetween(people, BILL, PAUL);
        System.out.println("Bill is " + days + " days older than Paul");
    }
}
