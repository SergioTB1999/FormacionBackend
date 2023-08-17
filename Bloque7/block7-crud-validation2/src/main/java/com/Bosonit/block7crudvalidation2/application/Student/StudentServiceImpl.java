package com.Bosonit.block7crudvalidation2.application.Student;

import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentFullOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentSimpleOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Person;
import com.Bosonit.block7crudvalidation2.domain.Student;
import com.Bosonit.block7crudvalidation2.repository.PersonRepository;
import com.Bosonit.block7crudvalidation2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StudentServiceImpl implements StudentService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    StudentRepository studentRepository;


    @Override
    public StudentFullOutputDto addStudent(StudentInputDto studentInputDto) {
        Person person = personRepository
                .findById(studentInputDto.getId_persona())
                .orElseThrow();
        Student student = new Student(studentInputDto);

        person.setStudent(student);
        student.setPerson(person);

        return studentRepository.save(student).studentToStudentOutputDto();
    }

    @Override
    public StudentFullOutputDto getStudentByIdFull(String id) {
        return studentRepository.findById(id).orElseThrow().studentToStudentOutputDto();
    }

    @Override
    public StudentSimpleOutputDto getStudentByIdSimple(String id) {
        return studentRepository.findById(id).orElseThrow().studentToStudentSimpleOutputDto();
    }

    @Override
    public List<StudentSimpleOutputDto> getAllStudents() {
        return studentRepository.findAll().stream().map(Student::studentToStudentSimpleOutputDto).toList();
    }

    @Override
    public StudentSimpleOutputDto updateStundent(StudentInputDto studentInputDto) {
        Student student = studentRepository.findById(studentInputDto.getId_student()).orElseThrow();

        student.setBranch(studentInputDto.getBranch());
        student.setComments(studentInputDto.getComments());
        student.setNum_hours_week(studentInputDto.getNum_hours_week());

        studentRepository.save(student);

        return student.studentToStudentSimpleOutputDto();
    }
}