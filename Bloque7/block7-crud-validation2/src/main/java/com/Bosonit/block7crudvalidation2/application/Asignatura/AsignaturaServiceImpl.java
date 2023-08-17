package com.Bosonit.block7crudvalidation2.application.Asignatura;

import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Asignatura;
import com.Bosonit.block7crudvalidation2.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaServiceImpl implements AsignaturaService{

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Override
    public AsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaInputDto) {
        return asignaturaRepository.save(new Asignatura(asignaturaInputDto)).subjectToSubjectOutputDto();
    }

    @Override
    public AsignaturaOutputDto getAsignaturaById(String id) {
        return asignaturaRepository.findById(id).orElseThrow().subjectToSubjectOutputDto();
    }
}