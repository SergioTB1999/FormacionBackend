package com.Bosonit.block7crudvalidation2.controller;

import com.Bosonit.block7crudvalidation2.application.Student.StudentServiceImpl;
import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentFullOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentSimpleOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Asignatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/estudiante")

public class ControllerStudent {

   @Autowired
    StudentServiceImpl studentService;

    @PostMapping
    public ResponseEntity<StudentFullOutputDto> addStudent(@RequestBody StudentInputDto studentInputDto){
        try{
            return ResponseEntity.ok().body(studentService.addStudent(studentInputDto));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/asignatura")
    public ResponseEntity<String> addAsignatura(@RequestParam String id_student, @RequestParam String id_asignatura){
        try{
            studentService.addStudentToAsignatura(id_student,id_asignatura);
            return ResponseEntity.ok().body("Asignatura con id " + id_asignatura + " añadido con exito al alumno con id " + id_student);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("No se encontraron las id o los parametros estan mal");
        }
    }

    @PostMapping("/asignarAsignaturas/{id}")
    public ResponseEntity<String> asignarAsignaturas(@PathVariable String id, @RequestParam List<String> id_asignaturas){
        try{
            studentService.asignarAsignaturasEstudiante(id,id_asignaturas);
            return ResponseEntity.ok().body("Asignaturas asignadas al estudiante con ID: " + id);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error al asignar");
        }
    }

    @PostMapping("/desasignarAsignaturas/{id}")
    public ResponseEntity<String> desasignarAsignaturas(@PathVariable String id, @RequestParam List<String> id_asignaturas){
        try{
            studentService.desasignarAsignaturasEstudiante(id, id_asignaturas);
            return ResponseEntity.ok().body("Asignaturas desasignadas al estudiantes con ID: " + id);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error al desasignar");
        }
    }

    @GetMapping("/{id_estudiante}")
    public ResponseEntity<?> getStudentById(
            @PathVariable String id_estudiante,
            @RequestParam(defaultValue = "simple", name = "outputType") String parametro){
        switch (parametro) {
            case "simple" -> {
                try {
                    studentService.getStudentByIdSimple(id_estudiante);
                    return ResponseEntity.ok().body(studentService.getStudentByIdSimple(id_estudiante));
                } catch (Exception e) {
                    return ResponseEntity.notFound().build();
                }
            }
            case "full" -> {
                try {
                    studentService.getStudentByIdFull(id_estudiante);
                    return ResponseEntity.ok().body(studentService.getStudentByIdFull(id_estudiante));
                } catch (Exception e) {
                    return ResponseEntity.notFound().build();
                }
            }
            default -> {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
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
        try{
            studentService.getStudentByIdSimple(studentInputDto.getId_student());
            return ResponseEntity.ok().body(studentService.updateStundent(studentInputDto));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
