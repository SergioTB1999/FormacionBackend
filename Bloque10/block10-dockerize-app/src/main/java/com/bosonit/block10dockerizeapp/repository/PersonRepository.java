package com.bosonit.block10dockerizeapp.repository;

import com.bosonit.block10dockerizeapp.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {
}
