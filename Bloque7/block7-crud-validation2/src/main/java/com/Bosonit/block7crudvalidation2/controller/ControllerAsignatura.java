package com.Bosonit.block7crudvalidation2.controller;

import com.Bosonit.block7crudvalidation2.application.Asignatura.AsignaturaService;
import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("asignatura")

public class ControllerAsignatura {

    @Autowired
    AsignaturaService asignaturaService;

    @PostMapping()
    public ResponseEntity<AsignaturaOutputDto> addAsignatura(@RequestBody AsignaturaInputDto asignaturaInputDto){
        return ResponseEntity.ok().body(asignaturaService.addAsignatura(asignaturaInputDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaOutputDto> getAsignaturaById(@PathVariable String id){
        try{
            asignaturaService.getAsignaturaById(id);
            return ResponseEntity.ok().body(asignaturaService.getAsignaturaById(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}