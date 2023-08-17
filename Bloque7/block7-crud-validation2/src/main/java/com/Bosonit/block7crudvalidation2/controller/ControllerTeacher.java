package com.Bosonit.block7crudvalidation2.controller;

import com.Bosonit.block7crudvalidation2.application.Teacher.TeacherService;
import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profesor")

public class ControllerTeacher {

    @Autowired
    TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherOutputDto> addProfesor(@RequestBody TeacherInputDto teacherInputDto){
        try{
            return ResponseEntity.ok().body(teacherService.addTeacher(teacherInputDto));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/estudiante")
    public ResponseEntity<String> addStudentToTeacher(@RequestParam String id_student, @RequestParam String id_profesor){
        try{
            teacherService.addStudentToTeacher(id_student,id_profesor);
            return ResponseEntity.ok().body("Student with id " + id_student + " added successfully to teacher with id " + id_profesor);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Params dont exists");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherOutputDto> getProfesorById(@PathVariable String id){
        try{
            teacherService.getTeacherById(id);
            return ResponseEntity.ok().body(teacherService.getTeacherById(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public Iterable<TeacherOutputDto> getAllProfesores(){
        return teacherService.getAllTeachers();
    }

    @PutMapping()
    public ResponseEntity<TeacherOutputDto> updateTeacher(@RequestBody TeacherInputDto teacherInputDto){
        try{
            teacherService.getTeacherById(teacherInputDto.getId_profesor());
            return ResponseEntity.ok().body(teacherService.updateTeacher(teacherInputDto));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
