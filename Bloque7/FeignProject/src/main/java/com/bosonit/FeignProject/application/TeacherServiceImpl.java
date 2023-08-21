package com.bosonit.FeignProject.application;

import com.bosonit.FeignProject.controller.dto.Teacher.TeacherInputDto;
import com.bosonit.FeignProject.controller.dto.Teacher.TeacherOutputDto;
import com.bosonit.FeignProject.domain.Person;
import com.bosonit.FeignProject.domain.Teacher;
import com.bosonit.FeignProject.repository.PersonRepository;
import com.bosonit.FeignProject.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    PersonRepository personRepository;
    @Override
    public TeacherOutputDto getTeacherById(String id) {
        return teacherRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID")
        ).teacherToTeacherOutputDto();
    }

    @Override
    public TeacherOutputDto addTeacher(TeacherInputDto teacherInputDto) {
        Person person = personRepository.findById(teacherInputDto.getId_persona()).orElseThrow();

        Teacher teacher = new Teacher(teacherInputDto);

        person.setTeacher(teacher);
        teacher.setPerson(person);

        return teacherRepository.save(teacher).teacherToTeacherOutputDto();
    }
}
