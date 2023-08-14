package com.bosonit.block7crud.controller;

import com.bosonit.block7crud.application.PersonServiceImpl;
import com.bosonit.block7crud.controller.dto.PersonInputDto;
import com.bosonit.block7crud.controller.dto.PersonOutputDto;
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
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person){
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.addPerson(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDto> updatePerson(@RequestBody PersonInputDto person){
        try{
            personService.getPersonById(String.valueOf(person.getId()));
            return ResponseEntity.ok().body(personService.addPerson(person));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonOutputDto> deletePerson(@PathVariable String id){
        try{
            personService.getPersonById(id);
            return ResponseEntity.ok().body(personService.getPersonById(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById(@PathVariable String id){
        try{
            personService.getPersonById(id);
            return ResponseEntity.ok().body(personService.getPersonById(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // TODO: 14/08/2023

    @GetMapping("/nombre/{nombre}")
    public Iterable<PersonOutputDto> getPersonsByName(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize,
            @PathVariable String nombre){
        return personService.getPersonByName(nombre, pageNumber,pageSize);
    }

    @GetMapping()
    public Iterable<PersonOutputDto> getAllPerson(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize){
        return personService.getAllPerson(pageNumber,pageSize);
    }
}
