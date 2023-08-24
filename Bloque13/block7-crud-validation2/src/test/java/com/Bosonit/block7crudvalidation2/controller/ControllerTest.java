package com.Bosonit.block7crudvalidation2.controller;

import com.Bosonit.block7crudvalidation2.Feign.TeacherFeignClient;
import com.Bosonit.block7crudvalidation2.application.Person.PersonServiceImpl;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherOutputDto;
import com.Bosonit.block7crudvalidation2.errors.CustomError;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.errors.UnprocessableEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

class ControllerTest {


    @InjectMocks
    private Controller controller;


    @Mock
    private PersonServiceImpl personService;

    @Mock
    private TeacherFeignClient teacherFeignClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleUnprocessableEntityException() {
    }

    @Test
    void handleEntityNotFoundException() {
    }

    @Test
    void addPerson() {

        PersonInputDto inputDto = new PersonInputDto();

        PersonOutputDto outputDto = new PersonOutputDto();
        when(personService.addPerson(any(PersonInputDto.class))).thenReturn(outputDto);

        ResponseEntity<?> response = controller.addPerson(inputDto);

        verify(personService).addPerson(inputDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(outputDto, response.getBody());
    }

    @Test
    void getPersonById() {

        String id = "123";
        String param = "simple";
        PersonOutputDto expectedOutput = new PersonOutputDto(); // crear un objeto de tipo PersonOutputDto esperado

        when(personService.getPerson(id, param)).thenReturn(expectedOutput);

        ResponseEntity<?> response = controller.getPersonById(id, param);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOutput, response.getBody());
    }

    @Test
    public void testGetTeacherById() {
        String id = "123";
        TeacherOutputDto expectedOutput = new TeacherOutputDto(); // crear un objeto de tipo TeacherOutputDto esperado

        when(teacherFeignClient.getProfesorById(id)).thenReturn(expectedOutput);

        ResponseEntity<TeacherOutputDto> response = controller.getTeacherById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOutput, response.getBody());
    }

    @Test
    public void testGetTeacherByIdNotFound() {
        String id = "123";

        when(teacherFeignClient.getProfesorById(id)).thenReturn(null);

        ResponseEntity<TeacherOutputDto> response = controller.getTeacherById(id);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    public void testGetPersons() {
        List<PersonOutputDto> personOutputDtos = new ArrayList<>(); // crear una lista de PersonOutputDto esperada
        when(personService.getAllPersons()).thenReturn(personOutputDtos);

        ResponseEntity<Iterable<PersonOutputDto>> response = controller.getPersons();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personOutputDtos, response.getBody());
    }

    @Test
    public void testUpdatePerson() {
        PersonInputDto inputDto = new PersonInputDto(); // crear un objeto de entrada
        PersonOutputDto personOutputDto = new PersonOutputDto(); // crear un objeto de salida esperado
        when(personService.updatePerson(inputDto)).thenReturn(personOutputDto);

        ResponseEntity<PersonOutputDto> response = controller.updatePerson(inputDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personOutputDto, response.getBody());
    }

    @Test
    public void testDeletePerson() {
        String id = "123"; // ID de ejemplo
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("La persona con el ID " + id + " se ha borrado correctamente");

        ResponseEntity<String> response = controller.deletePerson(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

}