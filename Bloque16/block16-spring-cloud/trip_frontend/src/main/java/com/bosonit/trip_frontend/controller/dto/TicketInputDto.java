package com.bosonit.trip_frontend.controller.dto;

import com.bosonit.trip_frontend.model.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TicketInputDto {

    private Long passangerId;
    private String passangerName;
    private String passangerLastName;
    private String passangerEmail;
    private String tripOrigin;
    private String tripDestination;
    private Date departureDate;
    private Date arrivalDate;


}
