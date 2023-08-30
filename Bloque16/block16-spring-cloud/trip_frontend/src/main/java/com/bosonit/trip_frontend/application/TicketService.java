package com.bosonit.trip_frontend.application;

import com.bosonit.trip_frontend.controller.dto.TicketInputDto;
import com.bosonit.trip_frontend.controller.dto.TicketOutputDto;

public interface TicketService {

    TicketOutputDto addTicket(Long id_cliente, Long id_viaje);
}
