package com.bosonit.trip_frontend.application;

import com.bosonit.trip_frontend.controller.dto.TicketInputDto;
import com.bosonit.trip_frontend.controller.dto.TicketOutputDto;
import com.bosonit.trip_frontend.model.Cliente;
import com.bosonit.trip_frontend.model.TicketEntity;
import com.bosonit.trip_frontend.model.Viaje;
import com.bosonit.trip_frontend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    RestTemplate restTemplate;
    @Override
    public TicketOutputDto addTicket(Long id_cliente, Long id_viaje) {
        String urlCliente = "http://localhost:8080/cliente/" + id_cliente;
        String urlViaje = "http://localhost:8080/trip/" + id_viaje;
        String urlAddPasajero = "http://localhost:8080/trip/addPassenger/{id_cliente}/{id_viaje}";
        Cliente cliente = restTemplate.getForObject(urlCliente, Cliente.class);
        Viaje viaje = restTemplate.getForObject(urlViaje, Viaje.class);
        restTemplate.postForEntity(urlAddPasajero,null,String.class,id_cliente,id_viaje);
        if (cliente != null && viaje != null){
            TicketInputDto ticketInputDto = new TicketInputDto(cliente.getId(),cliente.getNombre(),cliente.getApellido(),cliente.getEmail(),viaje.getOrigin(),viaje.getDestination(),
                    viaje.getDepartureDate(),viaje.getArrivalDate());
            return ticketRepository.save(new TicketEntity(ticketInputDto)).ticketToTicketOutputDto();
        } else {
            throw new RuntimeException("Error al encontrar al cliente o el viaje");
        }

    }
}
