package com.bosonit.trip_frontend.model;

import com.bosonit.trip_frontend.controller.dto.TicketInputDto;
import com.bosonit.trip_frontend.controller.dto.TicketOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")

public class TicketEntity {

    @Id
    @GeneratedValue
    @Column(name = "ticket_id")
    private Long id;
    @Column(name = "pasajero_id")
    private Long passangerId;
    @Column(name = "Nombre_Pasajero")
    private String passangerName;
    @Column(name = "Apellido_Pasajero")
    private String passangerLastName;
    @Column(name = "Email_Pasajero")
    private String passangerEmail;
    @Column(name = "Viaje_Origen")
    private String tripOrigin;
    @Column(name = "Viaje_Destino")
    private String tripDestination;
    @Column(name = "Fecha_Salida")
    private Date departureDate;
    @Column(name = "Fecha_Llegada")
    private Date arrivalDate;

    public TicketEntity(TicketInputDto ticketInputDto){
        this.passangerId = ticketInputDto.getPassangerId();
        this.passangerName = ticketInputDto.getPassangerName();
        this.passangerLastName = ticketInputDto.getPassangerLastName();
        this.passangerEmail = ticketInputDto.getPassangerEmail();
        this.tripOrigin = ticketInputDto.getTripOrigin();
        this.tripDestination = ticketInputDto.getTripDestination();
        this.departureDate = ticketInputDto.getDepartureDate();
        this.arrivalDate = ticketInputDto.getArrivalDate();
    }

    public TicketOutputDto ticketToTicketOutputDto(){
        return new TicketOutputDto(
                this.passangerId,
                this.passangerName,
                this.passangerLastName,
                this.passangerEmail,
                this.tripOrigin,
                this.tripDestination,
                this.departureDate,
                this.arrivalDate
        );
    }

}
