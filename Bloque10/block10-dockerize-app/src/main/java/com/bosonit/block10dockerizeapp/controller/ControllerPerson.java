package com.bosonit.block10dockerizeapp.controller;

import com.bosonit.block10dockerizeapp.application.PersonServiceImpl;
import com.bosonit.block10dockerizeapp.controller.dto.PersonInputDto;
import com.bosonit.block10dockerizeapp.controller.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")

public class ControllerPerson {

    @Autowired
    PersonServiceImpl personService;

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto personInputDto){
        return ResponseEntity.ok().body(personService.addPerson(personInputDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDto> getPersonId(@PathVariable int id){
        return ResponseEntity.ok().body(personService.getPersonId(id));
    }
}
