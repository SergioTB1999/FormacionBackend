package com.Bosonit.block7crudvalidation2.controller;

import com.Bosonit.block7crudvalidation2.application.Student.StudentServiceImpl;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentFullOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentSimpleOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Student;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.errors.UnprocessableEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControllerStudentTest {

    @InjectMocks
    private ControllerStudent controller;

    @Mock
    private StudentServiceImpl studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddStudent() {
        StudentInputDto inputDto = new StudentInputDto();
        StudentFullOutputDto outputDto = new StudentFullOutputDto();

        when(studentService.addStudent(inputDto)).thenReturn(outputDto);

        ResponseEntity<StudentFullOutputDto> response = controller.addStudent(inputDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(outputDto, response.getBody());
    }

    @Test
    public void testAddAsignatura() {
        String id_student = "1";
        String id_asignatura = "2";

        ResponseEntity<String> response = controller.addAsignatura(id_student, id_asignatura);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Asignatura con id " + id_asignatura + " añadido con exito al alumno con id " + id_student, response.getBody());
    }

    @Test
    public void testAsignarAsignaturas() {
        String id = "1";
        List<String> id_asignaturas = Collections.singletonList("2");

        ResponseEntity<String> response = controller.asignarAsignaturas(id, id_asignaturas);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Asignaturas asignadas al estudiante con ID: " + id, response.getBody());
    }

    @Test
    public void testDesasignarAsignaturas() {
        String id = "1";
        List<String> id_asignaturas = Collections.singletonList("2");

        ResponseEntity<String> response = controller.desasignarAsignaturas(id, id_asignaturas);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Asignaturas desasignadas al estudiantes con ID: " + id, response.getBody());

        verify(studentService).desasignarAsignaturasEstudiante(id, id_asignaturas);
    }

    @Test
    public void testGetStudentById() {
        String id = "1";
        String outputType = "simple";
        StudentSimpleOutputDto studentOutputDto = new StudentSimpleOutputDto();

        when(studentService.devuelveAlu(id, outputType)).thenReturn(studentOutputDto);

        ResponseEntity<?> response = controller.getStudentById(id, outputType);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(studentOutputDto, response.getBody());

        verify(studentService).devuelveAlu(id, outputType);
    }

    @Test
    public void testGetAllStudents() {
        List<StudentSimpleOutputDto> students = new ArrayList<>();
        students.add(new StudentSimpleOutputDto());

        when(studentService.getAllStudents()).thenReturn(students);

        Iterable<StudentSimpleOutputDto> response = controller.getAllStudents();

        assertEquals(students, response);

        verify(studentService).getAllStudents();
    }

    @Test
    public void testGetAsignaturas() {
        String id = "1";
        List<AsignaturaOutputDto> asignaturas = new ArrayList<>();
        asignaturas.add(new AsignaturaOutputDto());

        when(studentService.getAsignaturasByStudentId(id)).thenReturn(asignaturas);

        Iterable<AsignaturaOutputDto> response = controller.getAsignaturas(id);

        assertEquals(asignaturas, response);

        verify(studentService).getAsignaturasByStudentId(id);
    }

    @Test
    public void testUpdateStudent() {
        StudentInputDto inputDto = new StudentInputDto();
        StudentSimpleOutputDto updatedStudent = new StudentSimpleOutputDto();

        when(studentService.updateStundent(inputDto)).thenReturn(updatedStudent);

        ResponseEntity<StudentSimpleOutputDto> response = controller.updateStudent(inputDto);

        assertEquals(updatedStudent, response.getBody());
        assertEquals(200, response.getStatusCodeValue());

        verify(studentService).updateStundent(inputDto);
    }
    
}
