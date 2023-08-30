package com.bosonit.trip_backend.model;

import com.bosonit.trip_backend.controller.dto.ClienteInputDto;
import com.bosonit.trip_backend.controller.dto.ClienteOutputDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")

public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "edad")
    private int edad;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "viaje_id")
    private ViajeEntity viaje;

    public ClienteEntity(ClienteInputDto clienteInputDto){
        this.nombre = clienteInputDto.getNombre();
        this.apellido = clienteInputDto.getApellido();
        this.edad = clienteInputDto.getEdad();
        this.email = clienteInputDto.getEmail();
        this.telefono = clienteInputDto.getTelefono();
    }

    public ClienteOutputDto clienteToClienteOutputDto(){
        return new ClienteOutputDto(
                this.id,
                this.nombre,
                this.apellido,
                this.edad,
                this.email,
                this.telefono
        );
    }
}
