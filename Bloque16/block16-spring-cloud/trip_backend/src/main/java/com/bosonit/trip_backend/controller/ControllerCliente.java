package com.bosonit.trip_backend.controller;
import com.bosonit.trip_backend.application.ClienteServiceImpl;
import com.bosonit.trip_backend.controller.dto.ClienteInputDto;
import com.bosonit.trip_backend.controller.dto.ClienteOutputDto;
import com.bosonit.trip_backend.errors.CustomError;
import com.bosonit.trip_backend.errors.EntityNotFoundException;
import com.bosonit.trip_backend.errors.UnprocessableEntityException;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cliente")

public class ControllerCliente {

    @Autowired
    ClienteServiceImpl clienteService;

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
    public ResponseEntity<ClienteOutputDto> addCliente(@RequestBody ClienteInputDto clienteInputDto){
        return ResponseEntity.ok().body(clienteService.addCliente(clienteInputDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteOutputDto> getClienteById(@PathVariable Long id){
        return ResponseEntity.ok().body(clienteService.findClienteById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClienteOutputDto>> getAllClientes(){
        return ResponseEntity.ok().body(clienteService.getAllClientes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteOutputDto> updateClienteById(@PathVariable Long id, @RequestBody ClienteInputDto clienteInputDto){
        return ResponseEntity.ok().body(clienteService.updateCliente(id, clienteInputDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClienteById(@PathVariable Long id){
        clienteService.deleteCliente(id);
        return ResponseEntity.ok().body("El cliente se ha eliminado correctamente");
    }

}
