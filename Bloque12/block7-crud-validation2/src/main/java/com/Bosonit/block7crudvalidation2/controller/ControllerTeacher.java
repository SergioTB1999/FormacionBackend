package com.Bosonit.block7crudvalidation2.controller;

import com.Bosonit.block7crudvalidation2.application.Teacher.TeacherService;
import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherOutputDto;
import com.Bosonit.block7crudvalidation2.errors.CustomError;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.errors.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/profesor")

public class ControllerTeacher {

    @Autowired
    TeacherService teacherService;

    @ExceptionHandler
    @ResponseStatus
    public CustomError handleUnprocessableEntityException(UnprocessableEntityException ex){
        return new CustomError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus
    public CustomError handleEntityNotFoundException (EntityNotFoundException ex){
        return new CustomError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }

    @PostMapping
    public ResponseEntity<TeacherOutputDto> addProfesor(@RequestBody TeacherInputDto teacherInputDto){
            return ResponseEntity.ok().body(teacherService.addTeacher(teacherInputDto));
    }

    @PostMapping("/estudiante")
    public ResponseEntity<String> addStudentToTeacher(@RequestParam String id_student, @RequestParam String id_profesor){
            return ResponseEntity.ok().body("Student with id " + id_student + " added successfully to teacher with id " + id_profesor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherOutputDto> getProfesorById(@PathVariable String id){
            return ResponseEntity.ok().body(teacherService.getTeacherById(id));
    }

    @GetMapping()
    public Iterable<TeacherOutputDto> getAllProfesores(){
        return teacherService.getAllTeachers();
    }

    @PutMapping()
    public ResponseEntity<TeacherOutputDto> updateTeacher(@RequestBody TeacherInputDto teacherInputDto){
            return ResponseEntity.ok().body(teacherService.updateTeacher(teacherInputDto));
    }
}
