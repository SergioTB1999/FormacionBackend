package com.Bosonit.block7crudvalidation2.repository;

import com.Bosonit.block7crudvalidation2.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonRepository extends JpaRepository<Person,String>, JpaSpecificationExecutor<Person> {
}

