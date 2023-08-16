package com.bosonit.block7crudvalidation.controller;

import com.bosonit.block7crudvalidation.application.Teacher.TeacherService;
import com.bosonit.block7crudvalidation.controller.dto.Teacher.TeacherInputDto;
import com.bosonit.block7crudvalidation.controller.dto.Teacher.TeacherOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profesor")

public class ControllerTeacher {

    @Autowired
    TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherOutputDto> addProfesor(@RequestBody TeacherInputDto teacherInputDto){
        try{
            return ResponseEntity.ok().body(teacherService.addStudent(teacherInputDto));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
