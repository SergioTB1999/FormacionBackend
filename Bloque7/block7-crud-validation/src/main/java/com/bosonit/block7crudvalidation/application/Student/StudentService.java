package com.bosonit.block7crudvalidation.application.Student;


import com.bosonit.block7crudvalidation.controller.dto.Student.StudentInputDto;
import com.bosonit.block7crudvalidation.controller.dto.Student.StudentFullOutputDto;
import com.bosonit.block7crudvalidation.controller.dto.Student.StudentSimpleOutputDto;

public interface StudentService {

    StudentFullOutputDto addStudent(StudentInputDto studentInputDto);

    StudentFullOutputDto getStudentByIdFull(String id);

    StudentSimpleOutputDto getStudentByIdSimple(String id);
}
