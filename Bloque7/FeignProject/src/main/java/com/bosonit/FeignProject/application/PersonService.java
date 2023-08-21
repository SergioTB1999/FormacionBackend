package com.bosonit.FeignProject.application;

import com.bosonit.FeignProject.controller.dto.Person.PersonInputDto;
import com.bosonit.FeignProject.controller.dto.Person.PersonOutputDto;

public interface PersonService {

    PersonOutputDto addPerson(PersonInputDto personInputDto);
}
