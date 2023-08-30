package com.bosonit.trip_frontend.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Cliente {

    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String telefono;
}
