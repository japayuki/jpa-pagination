package dev.gtmedia.jpapagination.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import dev.gtmedia.jpapagination.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
