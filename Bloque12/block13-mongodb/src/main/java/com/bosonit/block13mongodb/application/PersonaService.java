package com.bosonit.block13mongodb.application;

import com.bosonit.block13mongodb.controller.dto.PersonInputDto;
import com.bosonit.block13mongodb.controller.dto.PersonOutputDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PersonaService {

    PersonOutputDto addPerson (PersonInputDto personInputDto);

    PersonOutputDto getPersonById (String id);

    Page<PersonOutputDto> getAllPersons (PageRequest pageRequest);

    void deletePersona (String id);

    PersonOutputDto updatePerson (PersonInputDto personInputDto);

}
