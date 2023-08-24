package com.Bosonit.block7crudvalidation2.repository;

import com.Bosonit.block7crudvalidation2.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,String> {
}

