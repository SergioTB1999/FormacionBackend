package com.Bosonit.block7crudvalidation2.application.Asignatura;

import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;

import java.util.List;

public interface AsignaturaService {

    AsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaInputDto);

    AsignaturaOutputDto getAsignaturaById(String id);

    List<AsignaturaOutputDto> getAllAsignaturas();

    AsignaturaOutputDto updateAsignatura(AsignaturaInputDto asignaturaInputDto);
}