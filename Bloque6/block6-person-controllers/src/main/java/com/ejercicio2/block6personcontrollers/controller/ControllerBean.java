package com.ejercicio2.block6personcontrollers.controller;

import com.ejercicio2.block6personcontrollers.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controladorBean")
public class ControllerBean {

    @Qualifier("bean1")
    private final Person bean1;
    @Qualifier("bean2")
    private final Person bean2;
    @Qualifier("bean3")
    private final Person bean3;


    public ControllerBean(Person bean1,Person bean2,Person bean3) {
        this.bean1 = bean1;
        this.bean2 = bean2;
        this.bean3 = bean3;
    }

    @GetMapping("/bean/{bean}")
    public Person getPersonBean(@PathVariable String bean){
        switch (bean){
            case "bean1":
                return bean1;
            case "bean2":
                return bean2;
            case "bean3":
                return bean3;
            default:
                return new Person("No corresponde a ningun Bean",0,"Unknown");
        }
    }
}
