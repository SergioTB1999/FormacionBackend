package com.Bosonit.block7crudvalidation2.repository;

import com.Bosonit.block7crudvalidation2.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,String> {
    Optional<Person> findByUsername(String username);
}

