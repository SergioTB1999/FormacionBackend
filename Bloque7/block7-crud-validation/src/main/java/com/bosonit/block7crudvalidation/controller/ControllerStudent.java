package com.bosonit.block7crudvalidation.controller;

import com.bosonit.block7crudvalidation.application.Student.StudentServiceImpl;
import com.bosonit.block7crudvalidation.controller.dto.Student.StudentInputDto;
import com.bosonit.block7crudvalidation.controller.dto.Student.StudentFullOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
