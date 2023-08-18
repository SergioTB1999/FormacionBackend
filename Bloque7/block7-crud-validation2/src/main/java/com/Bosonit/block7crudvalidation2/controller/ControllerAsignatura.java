package com.Bosonit.block7crudvalidation2.controller;

import com.Bosonit.block7crudvalidation2.application.Asignatura.AsignaturaService;
import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;
import com.Bosonit.block7crudvalidation2.errors.CustomError;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.errors.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("asignatura")

public class ControllerAsignatura {

    @Autowired
    AsignaturaService asignaturaService;

    @ExceptionHandler
    @ResponseStatus
    public CustomError handleUnprocessableEntityException(UnprocessableEntityException ex){
        return new CustomError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus
    public CustomError handleEntityNotFoundException (EntityNotFoundException ex){
        return new CustomError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }

    @PostMapping()
    public ResponseEntity<AsignaturaOutputDto> addAsignatura(@RequestBody AsignaturaInputDto asignaturaInputDto){
        return ResponseEntity.ok().body(asignaturaService.addAsignatura(asignaturaInputDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaOutputDto> getAsignaturaById(@PathVariable String id){
            return ResponseEntity.ok().body(asignaturaService.getAsignaturaById(id));
    }

    @GetMapping()
    public Iterable<AsignaturaOutputDto> getAllAsignaturas(){
        return asignaturaService.getAllAsignaturas();
    }

    @PutMapping()
    public ResponseEntity<AsignaturaOutputDto> updateAsignatura(@RequestBody AsignaturaInputDto asignaturaInputDto){
        return ResponseEntity.ok().body(asignaturaService.updateAsignatura(asignaturaInputDto));
    }

}