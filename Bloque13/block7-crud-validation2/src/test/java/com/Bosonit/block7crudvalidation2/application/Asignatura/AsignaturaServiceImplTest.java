package com.Bosonit.block7crudvalidation2.application.Asignatura;

import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Asignatura;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.repository.AsignaturaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AsignaturaServiceImplTest {

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @InjectMocks
    private AsignaturaServiceImpl asignaturaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAsignatura() {
        AsignaturaInputDto inputDto = new AsignaturaInputDto();
        inputDto.setAsignatura("Mates");
        inputDto.setComment("Muy dificiles");

        Asignatura asignatura = new Asignatura(inputDto);
        when(asignaturaRepository.save(any())).thenReturn(asignatura);

        AsignaturaOutputDto asignaturaOutputDto = asignaturaService.addAsignatura(inputDto);

        assertNotNull(asignaturaOutputDto);
        assertEquals("Mates", asignaturaOutputDto.getAsignatura());
        assertEquals("Muy dificiles", asignatura.getComment());
    }

    @Test
    void testGetAsignaturaById() {
        String id = "123";
        Asignatura asignatura = new Asignatura();
        when(asignaturaRepository.findById(id)).thenReturn(Optional.of(asignatura));

        AsignaturaOutputDto outputDto = asignaturaService.getAsignaturaById(id);

        assertNotNull(outputDto);
    }

    @Test
    void testGetAsignaturaById_EntityNotFoundException() {
        String id = "123";
        when(asignaturaRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> asignaturaService.getAsignaturaById(id)
        );

        assertEquals("No se encuentra la ID", exception.getMessage());
    }

    @Test
    void testGetAllAsignaturas() {
        List<Asignatura> asignaturas = new ArrayList<>();
        asignaturas.add(new Asignatura());
        when(asignaturaRepository.findAll()).thenReturn(asignaturas);

        List<AsignaturaOutputDto> outputDtos = asignaturaService.getAllAsignaturas();

        assertEquals(1, outputDtos.size());
    }

    @Test
    void testUpdateAsignatura() {
        AsignaturaInputDto inputDto = new AsignaturaInputDto();
        inputDto.setId_asignatura("123");
        inputDto.setAsignatura("Mates");
        inputDto.setComment("Updated Mates muy dificiles");

        Asignatura asignatura = new Asignatura();
        when(asignaturaRepository.findById("123")).thenReturn(Optional.of(asignatura));
        when(asignaturaRepository.save(any())).thenReturn(asignatura);

        AsignaturaOutputDto outputDto = asignaturaService.updateAsignatura(inputDto);

        assertNotNull(outputDto);
        assertEquals("Mates", outputDto.getAsignatura());
        assertEquals("Updated Mates muy dificiles", outputDto.getComment());
    }

    @Test
    void testUpdateAsignatura_EntityNotFoundException() {
        AsignaturaInputDto inputDto = new AsignaturaInputDto();
        inputDto.setId_asignatura("123");
        inputDto.setAsignatura("asig");
        inputDto.setComment("comen");
        inputDto.setInitialDate(new Date());
        inputDto.setFinishDate(new Date());

        when(asignaturaRepository.findById(inputDto.getId_asignatura())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> asignaturaService.updateAsignatura(inputDto)
        );

        assertEquals("No se encuentra la id de esa asignatura", exception.getMessage());
        verify(asignaturaRepository, never()).save(any());
    }



}
