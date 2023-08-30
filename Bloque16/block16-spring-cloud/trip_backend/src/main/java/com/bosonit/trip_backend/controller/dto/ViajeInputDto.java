package com.bosonit.trip_backend.controller.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViajeInputDto {

    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private boolean status;
}
