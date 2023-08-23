package com.bosonit.block13mongodb.application;

import com.bosonit.block13mongodb.controller.dto.PersonInputDto;
import com.bosonit.block13mongodb.controller.dto.PersonOutputDto;
import com.bosonit.block13mongodb.errors.EntityNotFoundException;
import com.bosonit.block13mongodb.errors.UnprocessableEntityException;
import com.bosonit.block13mongodb.model.Persona;
import com.bosonit.block13mongodb.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service

public class PersonaServiceImpl implements PersonaService{

    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonOutputDto addPerson(PersonInputDto personInputDto) {
        if (personInputDto.getUsuario() == null) throw new UnprocessableEntityException("Usuario no puede ser nulo");
        if (personInputDto.getUsuario().length() > 10) throw new UnprocessableEntityException("Longitud de usuario no puede ser mayor a 10");
        return personRepository.save(new Persona(personInputDto)).personToPersonOutputDto();
    }

    @Override
    public PersonOutputDto getPersonById(String id) {
        return personRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la persona por esa ID")
        ).personToPersonOutputDto();
    }

    @Override
    public Page<PersonOutputDto> getAllPersons(PageRequest pageRequest) {
        return personRepository.findAll(pageRequest)
                .map(Persona::personToPersonOutputDto);
    }

    @Override
    public void deletePersona(String id) {
        Persona persona = personRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la persona por esa ID")
        );

        personRepository.delete(persona);
    }

    @Override
    public PersonOutputDto updatePerson(PersonInputDto personInputDto) {
        Persona persona = personRepository.findById(personInputDto.getId()).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la persona por esa ID")
        );

        persona.setUsuario(personInputDto.getUsuario());
        persona.setPassword(personInputDto.getPassword());
        persona.setName(personInputDto.getName());
        persona.setSurname(personInputDto.getSurname());
        persona.setCompanyEmail(personInputDto.getCompanyEmail());
        persona.setPersonalEmail(personInputDto.getPersonalEmail());
        persona.setCity(personInputDto.getCity());
        persona.setActive(personInputDto.isActive());
        persona.setCreatedDate(personInputDto.getCreatedDate());
        persona.setImageUrl(personInputDto.getImageUrl());
        persona.setTerminationDate(personInputDto.getTerminationDate());

        personRepository.save(persona);

        return persona.personToPersonOutputDto();
    }
}
