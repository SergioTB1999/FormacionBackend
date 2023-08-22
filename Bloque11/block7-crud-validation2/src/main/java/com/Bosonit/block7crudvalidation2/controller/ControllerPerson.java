package com.Bosonit.block7crudvalidation2.controller;


import com.Bosonit.block7crudvalidation2.application.Person.PersonServiceImpl;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControllerPerson {

    @Autowired
    PersonServiceImpl personService;

    @CrossOrigin(origins = "https://cdpn.io")
    @PostMapping("/addperson")
    public ResponseEntity<PersonOutputDto> addPersonCors(@RequestBody PersonInputDto personInputDto){
        return ResponseEntity.ok().body(personService.addPerson(personInputDto));
    }

    @CrossOrigin(origins = "https://cdpn.io")
    @GetMapping("/getall")
    public Iterable<PersonOutputDto> getPersons(){
        return personService.getAllPersons();
    }
}
