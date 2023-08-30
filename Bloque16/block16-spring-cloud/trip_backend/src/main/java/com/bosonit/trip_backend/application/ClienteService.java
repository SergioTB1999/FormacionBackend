package com.bosonit.trip_backend.application;


import com.bosonit.trip_backend.controller.dto.ClienteInputDto;
import com.bosonit.trip_backend.controller.dto.ClienteOutputDto;

import java.util.List;

public interface ClienteService {

    ClienteOutputDto findClienteById(Long id);

    List<ClienteOutputDto> getAllClientes();

    ClienteOutputDto addCliente(ClienteInputDto clienteInputDto);

    ClienteOutputDto updateCliente(Long id, ClienteInputDto clienteInputDto);

    void deleteCliente(Long id);
}
