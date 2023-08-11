package com.ejercicio2.block6personcontrollers.service;

import com.ejercicio2.block6personcontrollers.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    public Person creaPersona(String name,int age,String town){
        return new Person(name,age,town);
    }
}
