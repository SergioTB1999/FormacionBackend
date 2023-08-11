package com.ejercicio2.block6personcontrollers.controller;

import com.ejercicio2.block6personcontrollers.service.CiudadService;
import com.ejercicio2.block6personcontrollers.model.Ciudad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controladorCiudad1")

public class ControllerCiudad1 {

    private final CiudadService ciudadService;

    @Autowired
    public ControllerCiudad1(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }


    @PostMapping("/addCiudad")
    public String addTownToList(@RequestBody Ciudad ciudad){
        ciudadService.addTown(ciudad);
        return "Ciudad añadida con exito";
    }
}
