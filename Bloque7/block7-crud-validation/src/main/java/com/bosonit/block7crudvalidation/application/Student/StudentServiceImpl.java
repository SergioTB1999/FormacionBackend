package com.bosonit.block7crudvalidation.application.Student;


import com.bosonit.block7crudvalidation.controller.dto.Student.StudentInputDto;
import com.bosonit.block7crudvalidation.controller.dto.Student.StudentFullOutputDto;
import com.bosonit.block7crudvalidation.controller.dto.Student.StudentSimpleOutputDto;
import com.bosonit.block7crudvalidation.domain.Person;
import com.bosonit.block7crudvalidation.domain.Student;
import com.bosonit.block7crudvalidation.repository.PersonRepository;
import com.bosonit.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
