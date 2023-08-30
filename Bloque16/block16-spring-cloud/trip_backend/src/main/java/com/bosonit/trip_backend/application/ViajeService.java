package com.bosonit.trip_backend.application;


import com.bosonit.trip_backend.controller.dto.ViajeInputDto;
import com.bosonit.trip_backend.controller.dto.ViajeOutputDto;

import java.util.List;

public interface ViajeService {

    ViajeOutputDto addViaje(ViajeInputDto viajeInputDto);

    ViajeOutputDto findViajeById(Long id);

    List<ViajeOutputDto> getAllViajes();

    ViajeOutputDto updateViaje(Long id, ViajeInputDto viajeInputDto);

    void deleteViaje(Long id);

    void addPasajeroToViaje(Long id_cliente, Long id_viaje);

    int countPasejerosByViaje(Long id_viaje);

    void updateStatusBus(Long id, boolean status);

    boolean getStatusBus(Long id);
}
