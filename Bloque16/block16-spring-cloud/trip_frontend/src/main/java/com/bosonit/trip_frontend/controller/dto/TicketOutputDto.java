package com.bosonit.trip_frontend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TicketOutputDto {

    private Long passangerId;
    private String passangerName;
    private String passangerLastName;
    private String passangerEmail;
    private String tripOrigin;
    private String tripDestination;
    private Date departureDate;
    private Date arrivalDate;


}