package com.bosonit.trip_backend.controller;
import com.bosonit.trip_backend.application.ViajeServiceImpl;
import com.bosonit.trip_backend.controller.dto.ViajeInputDto;
import com.bosonit.trip_backend.controller.dto.ViajeOutputDto;
import com.bosonit.trip_backend.errors.CustomError;
import com.bosonit.trip_backend.errors.EntityNotFoundException;
import com.bosonit.trip_backend.errors.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/trip")
public class ViajeController {

    @Autowired
    ViajeServiceImpl viajeService;

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

    @PostMapping
    public ResponseEntity<ViajeOutputDto> addViaje(@RequestBody ViajeInputDto viajeInputDto){
        return ResponseEntity.ok().body(viajeService.addViaje(viajeInputDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViajeOutputDto> getViajeById(@PathVariable Long id){
        return ResponseEntity.ok().body(viajeService.findViajeById(id));
    }

    @GetMapping
    public ResponseEntity<List<ViajeOutputDto>> getAllViajes(){
        return ResponseEntity.ok().body(viajeService.getAllViajes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViajeOutputDto> updateViaje(@PathVariable Long id, ViajeInputDto viajeInputDto){
        return ResponseEntity.ok().body(viajeService.updateViaje(id,viajeInputDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteViaje(@PathVariable Long id){
        viajeService.deleteViaje(id);
        return ResponseEntity.ok().body("Viaje eliminado con exito");
    }

    @PostMapping("/addPassenger/{id_cliente}/{id_viaje}")
    public ResponseEntity<String> addPasajeroToViaje(@PathVariable Long id_cliente, @PathVariable Long id_viaje){
        viajeService.addPasajeroToViaje(id_cliente, id_viaje);
        return ResponseEntity.ok().body("Se ha añadido al pasajero con ID: " + id_cliente + " al viaje con ID: " + id_viaje);
    }

    @GetMapping("/passenger/count/{id}")
    public ResponseEntity<String> countPasejeros(@PathVariable Long id){
        return ResponseEntity.ok().body("El numero total de pasajeros en este viaje es: " + viajeService.countPasejerosByViaje(id));
    }

    @PutMapping("/{id}/{tripStatus}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @PathVariable boolean tripStatus){
        viajeService.updateStatusBus(id,tripStatus);
        return ResponseEntity.ok().body("Se ha cambiado el estado del bus a: " + (tripStatus ? "Disponible" : "No disponible"));
    }

    @GetMapping("/verify/{id}")
    public ResponseEntity<String> getStatus(@PathVariable Long id){
        return ResponseEntity.ok().body("El estado del autobus es: " + (viajeService.getStatusBus(id) ? "Disponible" : "No disponible"));
    }
}
