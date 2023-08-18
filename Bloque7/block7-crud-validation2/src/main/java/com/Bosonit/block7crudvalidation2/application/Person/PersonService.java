package com.Bosonit.block7crudvalidation2.application.Person;

import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonStudentOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonTeacherOutputDto;

public interface PersonService {

    PersonOutputDto addPerson(PersonInputDto person);


    Iterable<PersonOutputDto> findPersonByName(String name);

    PersonTeacherOutputDto findPersonTeacherById(String id);

    PersonStudentOutputDto findePersonStudentById(String id);

    PersonOutputDto findPersonById(String id);

    Iterable<PersonOutputDto> getAllPersons();

    PersonOutputDto updatePerson(PersonInputDto personInputDto);

    void deletePersonById(String id);



}