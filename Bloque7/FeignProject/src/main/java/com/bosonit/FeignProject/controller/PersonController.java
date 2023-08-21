package com.bosonit.FeignProject.controller;

import com.bosonit.FeignProject.application.PersonServiceImpl;
import com.bosonit.FeignProject.controller.dto.Person.PersonInputDto;
import com.bosonit.FeignProject.controller.dto.Person.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")

public class PersonController {

    @Autowired
    PersonServiceImpl personService;

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto personInputDto){
        return ResponseEntity.ok().body(personService.addPerson(personInputDto));
    }
}
