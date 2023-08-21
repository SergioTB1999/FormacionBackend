package com.bosonit.block10dockerizeapp.application;

import com.bosonit.block10dockerizeapp.controller.dto.PersonInputDto;
import com.bosonit.block10dockerizeapp.controller.dto.PersonOutputDto;

public interface PersonService {

    PersonOutputDto addPerson(PersonInputDto personInputDto);

    PersonOutputDto getPersonId(int id);
}
