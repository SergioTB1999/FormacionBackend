package com.bosonit.block7crud.application;

import com.bosonit.block7crud.controller.dto.PersonInputDto;
import com.bosonit.block7crud.controller.dto.PersonOutputDto;
import com.bosonit.block7crud.domain.Person;

public interface PersonService {

    PersonOutputDto addPerson(PersonInputDto person);
    PersonOutputDto updatePerson(PersonInputDto person);
    void deletePersonById(String id);
    PersonOutputDto getPersonById(String id);
    Iterable<PersonOutputDto> getPersonByName(String nombre, int pageNumber, int pageSize);
    Iterable<PersonOutputDto> getAllPerson(int pageNumber, int pageSize);
}
