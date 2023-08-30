package com.bosonit.trip_backend.application;


import com.bosonit.trip_backend.controller.dto.ViajeInputDto;
import com.bosonit.trip_backend.controller.dto.ViajeOutputDto;
import com.bosonit.trip_backend.errors.EntityNotFoundException;
import com.bosonit.trip_backend.errors.UnprocessableEntityException;
import com.bosonit.trip_backend.model.ClienteEntity;
import com.bosonit.trip_backend.model.ViajeEntity;
import com.bosonit.trip_backend.repository.ClienteRepository;
import com.bosonit.trip_backend.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViajeServiceImpl implements ViajeService{

    @Autowired
    ViajeRepository viajeRepository;

    @Autowired
    ClienteRepository clienteRepository;
    @Override
    public ViajeOutputDto addViaje(ViajeInputDto viajeInputDto) {
        return viajeRepository.save(new ViajeEntity(viajeInputDto)).viajeToViajeOutputDto();
    }

    @Override
    public ViajeOutputDto findViajeById(Long id) {
        return viajeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID del viaje")
        ).viajeToViajeOutputDto();
    }

    @Override
    public List<ViajeOutputDto> getAllViajes() {
        return viajeRepository.findAll().stream().map(ViajeEntity::viajeToViajeOutputDto).toList();
    }

    @Override
    public ViajeOutputDto updateViaje(Long id, ViajeInputDto viajeInputDto) {
        ViajeEntity viajeEntity = viajeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID del viaje")
        );

        viajeEntity.setOrigin(viajeInputDto.getOrigin());
        viajeEntity.setDestination(viajeInputDto.getDestination());
        viajeEntity.setDepartureDate(viajeInputDto.getDepartureDate());
        viajeEntity.setArrivalDate(viajeInputDto.getArrivalDate());
        viajeEntity.setStatus(viajeInputDto.isStatus());

        return viajeRepository.save(viajeEntity).viajeToViajeOutputDto();
    }

    @Override
    public void deleteViaje(Long id) {
        viajeRepository.delete(
                viajeRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("No se encuentra la ID del viaje")
                )
        );
    }

    @Override
    public void addPasajeroToViaje(Long id_cliente, Long id_viaje) {
        ViajeEntity viajeEntity = viajeRepository.findById(id_viaje).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID del viaje")
        );

        ClienteEntity clienteEntity = clienteRepository.findById(id_cliente).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID del cliente")
        );

        if (viajeEntity.getClientes().size() > 40){
            throw  new UnprocessableEntityException("No quedan plazas para este autobus");
        }

        clienteEntity.setViaje(viajeEntity);
        viajeEntity.getClientes().add(clienteEntity);
        clienteRepository.save(clienteEntity);
        viajeRepository.save(viajeEntity);
    }

    @Override
    public int countPasejerosByViaje(Long id_viaje) {
        ViajeEntity viaje = viajeRepository.findById(id_viaje).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID del viaje")
        );

        return viaje.getClientes().size();
    }

    @Override
    public void updateStatusBus(Long id, boolean status) {
        ViajeEntity viajeEntity = viajeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID del viaje")
        );

        viajeEntity.setStatus(status);

        viajeRepository.save(viajeEntity);
    }

    @Override
    public boolean getStatusBus(Long id) {
        return viajeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID del viaje")
        ).isStatus();
    }
}
