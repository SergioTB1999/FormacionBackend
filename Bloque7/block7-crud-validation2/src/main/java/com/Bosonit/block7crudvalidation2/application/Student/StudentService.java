package com.Bosonit.block7crudvalidation2.application.Student;

import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentFullOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentSimpleOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Asignatura;

import java.util.List;
import java.util.Set;

public interface StudentService {

    StudentFullOutputDto addStudent(StudentInputDto studentInputDto);

    StudentFullOutputDto getStudentByIdFull(String id);

    StudentSimpleOutputDto getStudentByIdSimple(String id);

    List<StudentSimpleOutputDto> getAllStudents();

    StudentSimpleOutputDto updateStundent(StudentInputDto studentInputDto);

    void addStudentToAsignatura(String id_student, String id_asignatura);

    List<AsignaturaOutputDto> getAsignaturasByStudentId(String id);

    void asignarAsignaturasEstudiante(String id_student, List<String> id_asignatura);

    void desasignarAsignaturasEstudiante(String id_student, List<String> id_asignatura);
}
