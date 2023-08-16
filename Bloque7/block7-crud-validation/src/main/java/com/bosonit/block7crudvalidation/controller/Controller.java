package com.bosonit.block7crudvalidation.controller;

import com.bosonit.block7crudvalidation.application.Person.PersonServiceImpl;
import com.bosonit.block7crudvalidation.controller.dto.Person.PersonInputDto;
import com.bosonit.block7crudvalidation.controller.dto.Person.PersonOutputDto;
import com.bosonit.block7crudvalidation.errors.EntityNotFoundException;
import com.bosonit.block7crudvalidation.errors.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")

public class Controller {

    @Autowired
    PersonServiceImpl personService;

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person) {
        if (person.getUsuario() == null) throw new UnprocessableEntityException("El usuario es obligatorio");
        if (person.getUsuario().length() > 10) throw new EntityNotFoundException("El usuario no puede tener mas de 10 caracteres");
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.addPerson(person));
    }

    @GetMapping("/{id}")
    public PersonOutputDto getPersonById(@PathVariable String id) throws EntityNotFoundException {
        return personService.findPersonById(id);
    }

    @GetMapping()
    public Iterable<PersonOutputDto> getPersons(){
        return personService.getAllPersons();
    }
}
