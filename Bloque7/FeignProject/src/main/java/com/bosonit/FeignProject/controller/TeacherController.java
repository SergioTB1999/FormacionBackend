package com.bosonit.FeignProject.controller;

import com.bosonit.FeignProject.application.TeacherServiceImpl;
import com.bosonit.FeignProject.controller.dto.Teacher.TeacherInputDto;
import com.bosonit.FeignProject.controller.dto.Teacher.TeacherOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/profesor")

public class TeacherController {

    @Autowired
    TeacherServiceImpl teacherService;

    @PostMapping()
    public ResponseEntity<TeacherOutputDto> addTeacher(@RequestBody TeacherInputDto teacherInputDto){
        return ResponseEntity.ok().body(teacherService.addTeacher(teacherInputDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherOutputDto> getTeacher(@PathVariable String id){
        return ResponseEntity.ok().body(teacherService.getTeacherById(id));
    }
}
