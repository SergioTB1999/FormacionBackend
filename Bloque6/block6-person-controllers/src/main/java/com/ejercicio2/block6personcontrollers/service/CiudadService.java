package com.ejercicio2.block6personcontrollers.service;

import com.ejercicio2.block6personcontrollers.model.Ciudad;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadService {

    List<Ciudad> listTown = new ArrayList<>();


    public void addTown(Ciudad ciudad){
        listTown.add(ciudad);
    }

    public List<Ciudad> devuelveTowns(){
        return listTown;
    }
}
