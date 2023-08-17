package com.Bosonit.block7crudvalidation2.application.Teacher;

import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Person;
import com.Bosonit.block7crudvalidation2.domain.Student;
import com.Bosonit.block7crudvalidation2.domain.Teacher;
import com.Bosonit.block7crudvalidation2.repository.PersonRepository;
import com.Bosonit.block7crudvalidation2.repository.StudentRepository;
import com.Bosonit.block7crudvalidation2.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public TeacherOutputDto addTeacher(TeacherInputDto teacherInputDto) {
        Person person = personRepository.findById(teacherInputDto.getId_persona()).orElseThrow();
        Teacher teacher = new Teacher(teacherInputDto);

        person.setTeacher(teacher);
        teacher.setPerson(person);

        return teacherRepository.save(teacher).teacherToTeacherOutputDto();

    }

    @Override
    public TeacherOutputDto getTeacherById(String id) {
        return teacherRepository.findById(id).orElseThrow().teacherToTeacherOutputDto();
    }

    @Override
    public List<TeacherOutputDto> getAllTeachers() {
        return teacherRepository.findAll().stream().map(Teacher::teacherToTeacherOutputDto).toList();
    }

    @Override
    public TeacherOutputDto updateTeacher(TeacherInputDto teacherInputDto) {
        Teacher teacher = teacherRepository.findById(teacherInputDto.getId_profesor()).orElseThrow();

        teacher.setBranch(teacherInputDto.getBranch());
        teacher.setComments(teacherInputDto.getComments());

        teacherRepository.save(teacher);

        return teacher.teacherToTeacherOutputDto();
    }

    @Override
    public void addStudentToTeacher(String id_student, String id_profesor) {
        Student student = studentRepository.findById(id_student).orElseThrow();
        Teacher teacher = teacherRepository.findById(id_profesor).orElseThrow();

        student.setTeacher(teacher);
        teacher.getStudents().add(student);

        studentRepository.save(student);
        teacherRepository.save(teacher);
    }


}
