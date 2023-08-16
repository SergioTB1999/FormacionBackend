package com.bosonit.block7crudvalidation.application.Person;

import com.bosonit.block7crudvalidation.controller.dto.Person.PersonInputDto;
import com.bosonit.block7crudvalidation.controller.dto.Person.PersonOutputDto;
import com.bosonit.block7crudvalidation.domain.Person;
import com.bosonit.block7crudvalidation.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonOutputDto addPerson(PersonInputDto person){
        return personRepository.save(new Person(person)).personToPersonOutputDto();
    }

    @Override
    public PersonOutputDto findPersonById(String id) {
        return personRepository.findById(id).orElseThrow().personToPersonOutputDto();
    }

    @Override
    public Iterable<PersonOutputDto> findPersonByName(String name) {
        return personRepository.findAll().stream().filter(
                person -> person.getName().equals(name)
        ).map(Person::personToPersonOutputDto).toList();
    }

    @Override
    public Iterable<PersonOutputDto> getAllPersons() {
        return personRepository.findAll()
                .stream()
                .map(Person::personToPersonOutputDto).toList();
    }
}
