package com.bosonit.trip_backend.controller.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteOutputDto {

    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String telefono;
}
