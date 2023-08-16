package com.bosonit.block7crudvalidation.application.Teacher;

import com.bosonit.block7crudvalidation.controller.dto.Teacher.TeacherInputDto;
import com.bosonit.block7crudvalidation.controller.dto.Teacher.TeacherOutputDto;
import com.bosonit.block7crudvalidation.domain.Person;
import com.bosonit.block7crudvalidation.domain.Teacher;
import com.bosonit.block7crudvalidation.repository.PersonRepository;
import com.bosonit.block7crudvalidation.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TeacherRepository teacherRepository;
    @Override
    public TeacherOutputDto addStudent(TeacherInputDto teacherInputDto) {
        Person person = personRepository.findById(teacherInputDto.getId_persona()).orElseThrow();
        Teacher teacher = new Teacher(teacherInputDto);

        person.setTeacher(teacher);
        teacher.setPerson(person);

        return teacherRepository.save(teacher).teacherToTeacherOutputDto();

    }
}
