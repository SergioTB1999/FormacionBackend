package com.Bosonit.block7crudvalidation2.application.Asignatura;

import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Asignatura;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return asignaturaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID")
        ).subjectToSubjectOutputDto();
    }

    @Override
    public List<AsignaturaOutputDto> getAllAsignaturas() {
        return asignaturaRepository.findAll()
                .stream()
                .map(Asignatura::subjectToSubjectOutputDto).toList();
    }

    @Override
    public AsignaturaOutputDto updateAsignatura(AsignaturaInputDto asignaturaInputDto) {
        Asignatura asignatura = asignaturaRepository.findById(asignaturaInputDto.getId_asignatura()).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la id de esa asignatura")
        );

        asignatura.setAsignatura(asignaturaInputDto.getAsignatura());
        asignatura.setComment(asignaturaInputDto.getComment());
        asignatura.setInitialDate(asignaturaInputDto.getInitialDate());
        asignatura.setFinishDate(asignaturaInputDto.getFinishDate());

        asignaturaRepository.save(asignatura);

        return asignatura.subjectToSubjectOutputDto();
    }
}