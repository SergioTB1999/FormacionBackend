package com.bosonit.block7crudvalidation.application.Person;

import com.bosonit.block7crudvalidation.controller.dto.Person.PersonInputDto;
import com.bosonit.block7crudvalidation.controller.dto.Person.PersonOutputDto;

public interface PersonService {

    PersonOutputDto addPerson(PersonInputDto person);

    PersonOutputDto findPersonById(String id);

    Iterable<PersonOutputDto> findPersonByName(String name);

    Iterable<PersonOutputDto> getAllPersons();

}
