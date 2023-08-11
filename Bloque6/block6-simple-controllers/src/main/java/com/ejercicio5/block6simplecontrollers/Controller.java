package com.ejercicio5.block6simplecontrollers;

import model.Person;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @GetMapping("/user/{nombre}")
    public String devuelveNombre(@PathVariable String nombre){
        return "Hola " + nombre;
    }

    @PostMapping("/useradd")
    public Person devuelvePersona(@RequestBody Person person){
        person.setAge(person.getAge() + 1);
        return person;
    }
}
