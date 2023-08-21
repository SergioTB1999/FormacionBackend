package com.bosonit.FeignProject.application;

import com.bosonit.FeignProject.controller.dto.Person.PersonInputDto;
import com.bosonit.FeignProject.controller.dto.Person.PersonOutputDto;
import com.bosonit.FeignProject.domain.Person;
import com.bosonit.FeignProject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonOutputDto addPerson(PersonInputDto personInputDto) {
        return personRepository.save(new Person(personInputDto)).personToPersonOutputDto();
    }
}
