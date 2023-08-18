package com.Bosonit.block7crudvalidation2.controller;

import com.Bosonit.block7crudvalidation2.application.Student.StudentServiceImpl;
import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentFullOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentSimpleOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Asignatura;
import com.Bosonit.block7crudvalidation2.errors.CustomError;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.errors.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/estudiante")

public class ControllerStudent {

   @Autowired
    StudentServiceImpl studentService;

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
    public ResponseEntity<StudentFullOutputDto> addStudent(@RequestBody StudentInputDto studentInputDto){
            return ResponseEntity.ok().body(studentService.addStudent(studentInputDto));
    }

    @PostMapping("/asignatura")
    public ResponseEntity<String> addAsignatura(@RequestParam String id_student, @RequestParam String id_asignatura){
            return ResponseEntity.ok().body("Asignatura con id " + id_asignatura + " añadido con exito al alumno con id " + id_student);
    }

    @PostMapping("/asignarAsignaturas/{id}")
    public ResponseEntity<String> asignarAsignaturas(@PathVariable String id, @RequestParam List<String> id_asignaturas){
            return ResponseEntity.ok().body("Asignaturas asignadas al estudiante con ID: " + id);
    }

    @PostMapping("/desasignarAsignaturas/{id}")
    public ResponseEntity<String> desasignarAsignaturas(@PathVariable String id, @RequestParam List<String> id_asignaturas){
            return ResponseEntity.ok().body("Asignaturas desasignadas al estudiantes con ID: " + id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(
            @PathVariable String id,
            @RequestParam(defaultValue = "simple", name = "outputType") String parametro){
        return ResponseEntity.ok().body(studentService.devuelveAlu(id,parametro));
    }

    @GetMapping()
    public Iterable<StudentSimpleOutputDto> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/estudiante_asignatura/{id}")
    public Iterable<AsignaturaOutputDto> getAsignaturas(@PathVariable String id){
        return studentService.getAsignaturasByStudentId(id);
    }

    @PutMapping()
    public ResponseEntity<StudentSimpleOutputDto> updateStudent(@RequestBody StudentInputDto studentInputDto){
            return ResponseEntity.ok().body(studentService.updateStundent(studentInputDto));
    }

}
