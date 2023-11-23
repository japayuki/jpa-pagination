package dev.gtmedia.jpapagination.data;

import com.github.javafaker.Faker;
import dev.gtmedia.jpapagination.model.Address;
import dev.gtmedia.jpapagination.model.Person;
import dev.gtmedia.jpapagination.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;


public class SampleDataLoader implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(SampleDataLoader.class);
    private final PersonRepository personRepository;
    @Autowired
    private Faker faker;

    public SampleDataLoader(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading Sample Data...");
        List<Person> people = IntStream.rangeClosed(1,100)
                .mapToObj(i -> new Person(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.phoneNumber().phoneNumber(),
                        faker.internet().emailAddress(),
                        new Address(
                                faker.address().secondaryAddress(),
                                faker.address().city(),
                                faker.address().state(),
                                faker.address().zipCode()
                        )
                )).toList();
        personRepository.saveAll(people);



    }
}
