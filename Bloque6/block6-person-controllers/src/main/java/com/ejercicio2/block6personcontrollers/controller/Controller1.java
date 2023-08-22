package com.ejercicio2.block6personcontrollers.controller;

import com.ejercicio2.block6personcontrollers.service.PersonService;
import com.ejercicio2.block6personcontrollers.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador1")
public class Controller1 {

    @Autowired
    PersonService personService;

    @Autowired
    public Controller1(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/addPersona")
    public Person addPersona(String name, String town, int age){
        return personService.creaPersona(name, age, town);
    }
}
