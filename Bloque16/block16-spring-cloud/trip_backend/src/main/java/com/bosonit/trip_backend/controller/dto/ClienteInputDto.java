package com.bosonit.trip_backend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteInputDto {


    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String telefono;
}
