package com.bosonit.trip_frontend.controller;

import com.bosonit.trip_frontend.application.TicketServiceImpl;
import com.bosonit.trip_frontend.controller.dto.TicketInputDto;
import com.bosonit.trip_frontend.controller.dto.TicketOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class Controller {

    @Autowired
    TicketServiceImpl ticketService;

    @PostMapping("/{id_cliente}/{id_viaje}")
    public ResponseEntity<TicketOutputDto> prueba(@PathVariable Long id_cliente, @PathVariable Long id_viaje){
        return ResponseEntity.ok().body(ticketService.addTicket(id_cliente, id_viaje));
    }
}
