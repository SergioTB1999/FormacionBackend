package com.ejercicio2.block6personcontrollers.controller;

import com.ejercicio2.block6personcontrollers.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador2")
public class Controller2 {


    @Autowired
    Controller1 controller1;


    @Autowired
    public Controller2(Controller1 controller1) {
        this.controller1 = controller1;
    }

    @GetMapping("/getPersona")
    public Person getPersona(@RequestHeader String name, @RequestHeader int age, @RequestHeader String town){
        return controller1.addPersona(name,town,age * 2);
    }


}
