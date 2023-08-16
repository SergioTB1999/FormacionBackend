package com.bosonit.block7crudvalidation.application.Teacher;
import com.bosonit.block7crudvalidation.controller.dto.Teacher.TeacherInputDto;
import com.bosonit.block7crudvalidation.controller.dto.Teacher.TeacherOutputDto;

public interface TeacherService {

    TeacherOutputDto addStudent(TeacherInputDto teacherInputDto);
}
