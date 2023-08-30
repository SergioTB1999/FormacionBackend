package com.bosonit.trip_backend.application;
import com.bosonit.trip_backend.controller.dto.ClienteInputDto;
import com.bosonit.trip_backend.controller.dto.ClienteOutputDto;
import com.bosonit.trip_backend.errors.EntityNotFoundException;
import com.bosonit.trip_backend.model.ClienteEntity;
import com.bosonit.trip_backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ClienteServiceImpl implements ClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public ClienteOutputDto findClienteById(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID del cliente")
        ).clienteToClienteOutputDto();
    }

    @Override
    public List<ClienteOutputDto> getAllClientes() {
        return clienteRepository.findAll().stream().map(ClienteEntity::clienteToClienteOutputDto).toList();
    }

    @Override
    public ClienteOutputDto addCliente(ClienteInputDto clienteInputDto) {
        return clienteRepository.save(new ClienteEntity(clienteInputDto)).clienteToClienteOutputDto();
    }

    @Override
    public ClienteOutputDto updateCliente(Long id, ClienteInputDto clienteInputDto) {
        ClienteEntity clienteEntity = clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID del cliente")
        );

        clienteEntity.setNombre(clienteInputDto.getNombre());
        clienteEntity.setApellido(clienteInputDto.getApellido());
        clienteEntity.setEdad(clienteInputDto.getEdad());
        clienteEntity.setEmail(clienteInputDto.getEmail());
        clienteEntity.setTelefono(clienteInputDto.getTelefono());

        return clienteRepository.save(clienteEntity).clienteToClienteOutputDto();

    }

    @Override
    public void deleteCliente(Long id) {
        ClienteEntity clienteEntity = clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID del cliente")
        );

        clienteRepository.delete(clienteEntity);
    }
}
