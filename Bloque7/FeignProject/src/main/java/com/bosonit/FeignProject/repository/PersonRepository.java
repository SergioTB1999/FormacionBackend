package com.bosonit.FeignProject.repository;

import com.bosonit.FeignProject.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,String> {
}
