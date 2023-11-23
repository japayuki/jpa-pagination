package dev.gtmedia.jpapagination.controller;

import dev.gtmedia.jpapagination.model.Person;
import dev.gtmedia.jpapagination.model.PersonDTO;
import dev.gtmedia.jpapagination.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final PersonRepository personRepository;


    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public Page<Person> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "firstName") String sortField, @RequestParam(defaultValue = "ASC") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :  Sort.by(sortField).descending();
        Pageable p = PageRequest.of(page, size, sort);
        Pageable p1 = PageRequest.of(page, size);
        Page<Person> pg = personRepository.findAll(p1);
        List<Person> people = personRepository.findAll();
        List<PersonDTO> peopleDTO = people.stream()
                .map(pd -> new PersonDTO(
                    pd.getFirstName(),
                    pd.getLastName()
                 ))
                .toList();
        return pg;
    }

}
