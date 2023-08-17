package com.Bosonit.block7crudvalidation2.application.Student;

import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentFullOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentSimpleOutputDto;

import java.util.List;

public interface StudentService {

    StudentFullOutputDto addStudent(StudentInputDto studentInputDto);

    StudentFullOutputDto getStudentByIdFull(String id);

    StudentSimpleOutputDto getStudentByIdSimple(String id);

    List<StudentSimpleOutputDto> getAllStudents();

    StudentSimpleOutputDto updateStundent(StudentInputDto studentInputDto);
}
