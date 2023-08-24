package com.Bosonit.block7crudvalidation2.controller;

import com.Bosonit.block7crudvalidation2.application.Asignatura.AsignaturaService;
import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.errors.UnprocessableEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControllerAsignaturaTest {

    @InjectMocks
    private ControllerAsignatura controller;

    @Mock
    private AsignaturaService asignaturaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddAsignatura() {
        AsignaturaInputDto inputDto = new AsignaturaInputDto();
        AsignaturaOutputDto outputDto = new AsignaturaOutputDto();

        when(asignaturaService.addAsignatura(inputDto)).thenReturn(outputDto);

        ResponseEntity<AsignaturaOutputDto> response = controller.addAsignatura(inputDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(outputDto, response.getBody());
    }

    @Test
    public void testGetAsignaturaById() {
        String id = "1";
        AsignaturaOutputDto outputDto = new AsignaturaOutputDto();

        when(asignaturaService.getAsignaturaById(id)).thenReturn(outputDto);

        ResponseEntity<AsignaturaOutputDto> response = controller.getAsignaturaById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(outputDto, response.getBody());
    }

    @Test
    public void testGetAllAsignaturas() {
        List<AsignaturaOutputDto> outputDtoList = Collections.singletonList(new AsignaturaOutputDto());

        when(asignaturaService.getAllAsignaturas()).thenReturn(outputDtoList);

        Iterable<AsignaturaOutputDto> result = controller.getAllAsignaturas();

        assertEquals(outputDtoList, result);
    }

    @Test
    public void testUpdateAsignatura() {
        AsignaturaInputDto inputDto = new AsignaturaInputDto();
        AsignaturaOutputDto outputDto = new AsignaturaOutputDto();

        when(asignaturaService.updateAsignatura(inputDto)).thenReturn(outputDto);

        ResponseEntity<AsignaturaOutputDto> response = controller.updateAsignatura(inputDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(outputDto, response.getBody());
    }

    // You can write more tests for exception handling if needed
}