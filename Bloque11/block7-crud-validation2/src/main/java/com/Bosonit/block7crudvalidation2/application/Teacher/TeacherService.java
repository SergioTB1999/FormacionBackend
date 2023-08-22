package com.Bosonit.block7crudvalidation2.application.Teacher;

import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherOutputDto;

import java.util.List;

public interface TeacherService {

    TeacherOutputDto addTeacher(TeacherInputDto teacherInputDto);

    TeacherOutputDto getTeacherById(String id);

    List<TeacherOutputDto> getAllTeachers();

    TeacherOutputDto updateTeacher(TeacherInputDto teacherInputDto);

    void addStudentToTeacher(String id_student, String id_profesor);
}

