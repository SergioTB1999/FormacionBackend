package com.Bosonit.block7crudvalidation2.controller;

import com.Bosonit.block7crudvalidation2.application.Teacher.TeacherService;
import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherOutputDto;
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

public class ControllerTeacherTest {

    @InjectMocks
    private ControllerTeacher controllerTeacher;

    @Mock
    private TeacherService teacherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProfesor() {
        TeacherInputDto teacherInputDto = new TeacherInputDto();
        TeacherOutputDto teacherOutputDto = new TeacherOutputDto();

        when(teacherService.addTeacher(teacherInputDto)).thenReturn(teacherOutputDto);

        ResponseEntity<TeacherOutputDto> response = controllerTeacher.addProfesor(teacherInputDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teacherOutputDto, response.getBody());

        verify(teacherService, times(1)).addTeacher(teacherInputDto);
        verifyNoMoreInteractions(teacherService);
    }

    @Test
    public void testAddStudentToTeacher() {
        String idStudent = "student_id";
        String idTeacher = "teacher_id";

        ResponseEntity<String> response = controllerTeacher.addStudentToTeacher(idStudent, idTeacher);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Student with id " + idStudent + " added successfully to teacher with id " + idTeacher, response.getBody());
    }

    @Test
    public void testGetProfesorById() {
        String id = "teacher_id";
        TeacherOutputDto teacherOutputDto = new TeacherOutputDto();

        when(teacherService.getTeacherById(id)).thenReturn(teacherOutputDto);

        ResponseEntity<TeacherOutputDto> response = controllerTeacher.getProfesorById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teacherOutputDto, response.getBody());

        verify(teacherService, times(1)).getTeacherById(id);
        verifyNoMoreInteractions(teacherService);
    }

    @Test
    public void testGetAllProfesores() {
        List<TeacherOutputDto> teachers = Collections.singletonList(new TeacherOutputDto());

        when(teacherService.getAllTeachers()).thenReturn(teachers);

        Iterable<TeacherOutputDto> response = controllerTeacher.getAllProfesores();

        assertEquals(teachers, response);

        verify(teacherService, times(1)).getAllTeachers();
        verifyNoMoreInteractions(teacherService);
    }

    @Test
    public void testUpdateTeacher() {
        TeacherInputDto teacherInputDto = new TeacherInputDto();
        TeacherOutputDto teacherOutputDto = new TeacherOutputDto();

        when(teacherService.updateTeacher(teacherInputDto)).thenReturn(teacherOutputDto);

        ResponseEntity<TeacherOutputDto> response = controllerTeacher.updateTeacher(teacherInputDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teacherOutputDto, response.getBody());

        verify(teacherService, times(1)).updateTeacher(teacherInputDto);
        verifyNoMoreInteractions(teacherService);
    }

    // Add more tests for exception handling and edge cases...
}
