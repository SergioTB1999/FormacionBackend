package com.bosonit.block7crudvalidation.repository;

import com.bosonit.block7crudvalidation.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,String> {
}
