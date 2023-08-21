package com.bosonit.block10dockerizeapp.application;

import com.bosonit.block10dockerizeapp.controller.dto.PersonInputDto;
import com.bosonit.block10dockerizeapp.controller.dto.PersonOutputDto;
import com.bosonit.block10dockerizeapp.domain.Person;
import com.bosonit.block10dockerizeapp.repository.PersonRepository;
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

    @Override
    public PersonOutputDto getPersonId(int id) {
        return personRepository.findById(id).orElseThrow().personToPersonOutputDto();
    }
}
