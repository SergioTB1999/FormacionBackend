package com.bosonit.trip_backend.model;
import com.bosonit.trip_backend.controller.dto.ViajeInputDto;
import com.bosonit.trip_backend.controller.dto.ViajeOutputDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "viaje")
public class ViajeEntity {

    @Id
    @GeneratedValue
    @Column(name = "viaje_id")
    private Long id;
    @Column(name = "origen")
    private String origin;
    @Column(name = "destino")
    private String destination;
    @Column(name = "fecha_salida")
    private Date departureDate;
    @Column(name = "fecha_llegada")
    private Date arrivalDate;
    @Column(name = "estado")
    private boolean status;
    @OneToMany(mappedBy = "viaje")
    private Set<ClienteEntity> clientes;

    public ViajeEntity(ViajeInputDto viajeInputDto){
        this.origin = viajeInputDto.getOrigin();
        this.destination = viajeInputDto.getDestination();
        this.departureDate = viajeInputDto.getDepartureDate();
        this.arrivalDate = viajeInputDto.getArrivalDate();
        this.status = viajeInputDto.isStatus();
    }

    public ViajeOutputDto viajeToViajeOutputDto(){
        return new ViajeOutputDto(
                this.id,
                this.origin,
                this.destination,
                this.departureDate,
                this.arrivalDate,
                this.status
        );
    }

}
