package com.Bosonit.block7crudvalidation2.application.Student;

import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentFullOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentSimpleOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Asignatura;
import com.Bosonit.block7crudvalidation2.domain.Person;
import com.Bosonit.block7crudvalidation2.domain.Student;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.repository.AsignaturaRepository;
import com.Bosonit.block7crudvalidation2.repository.PersonRepository;
import com.Bosonit.block7crudvalidation2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service

public class StudentServiceImpl implements StudentService {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AsignaturaRepository asignaturaRepository;

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

    @Override
    public void addStudentToAsignatura(String id_student, String id_asignatura) {
        Student student = studentRepository.findById(id_student).orElseThrow();

        Asignatura asignatura = asignaturaRepository.findById(id_asignatura).orElseThrow();

        asignatura.setStudent(student);
        student.getAsignaturas().add(asignatura);

        studentRepository.save(student);
        asignaturaRepository.save(asignatura);
    }

    @Override
    public List<AsignaturaOutputDto> getAsignaturasByStudentId(String id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado al estudiante"));

        return student.getAsignaturas()
                .stream()
                .map(Asignatura::subjectToSubjectOutputDto).toList();
    }

    @Override
    public void asignarAsignaturasEstudiante(String id_student, List<String> id_asignatura) {
        Student student = studentRepository.findById(id_student).orElseThrow();

        List<Asignatura> asignaturas = asignaturaRepository.findAllById(id_asignatura);

        student.getAsignaturas().addAll(asignaturas);

        for (Asignatura a:
                asignaturas) {
            a.setStudent(student);
            asignaturaRepository.save(a);
        }

        studentRepository.save(student);
    }

    @Override
    public void desasignarAsignaturasEstudiante(String id_student, List<String> id_asignatura) {
        Student student = studentRepository.findById(id_student).orElseThrow();

        List<Asignatura> asignaturas = asignaturaRepository.findAllById(id_asignatura);

        student.getAsignaturas().removeAll(asignaturas);

        for (Asignatura a:
                asignaturas) {
            a.setStudent(null);
            asignaturaRepository.save(a);
        }

        studentRepository.save(student);

    }
}