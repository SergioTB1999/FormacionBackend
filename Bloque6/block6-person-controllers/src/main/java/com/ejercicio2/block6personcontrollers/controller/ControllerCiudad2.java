package com.ejercicio2.block6personcontrollers.controller;

import com.ejercicio2.block6personcontrollers.service.CiudadService;
import com.ejercicio2.block6personcontrollers.model.Ciudad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controladorCiudad2")
public class ControllerCiudad2 {

    private final CiudadService ciudadService;

    @Autowired
    public ControllerCiudad2(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @GetMapping("/getCiudades")
    public List<Ciudad> listTown(){
        return ciudadService.devuelveTowns();
    }
}
