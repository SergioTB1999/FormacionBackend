package com.Bosonit.block7crudvalidation2.application.Student;

import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentFullOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentSimpleOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Asignatura;
import com.Bosonit.block7crudvalidation2.domain.Person;
import com.Bosonit.block7crudvalidation2.domain.Student;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.errors.UnprocessableEntityException;
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
                .orElseThrow(
                        () -> new EntityNotFoundException("No se encuentra la ID del alumno")
                );
        Student student = new Student(studentInputDto);

        person.setStudent(student);
        student.setPerson(person);

        return studentRepository.save(student).studentToStudentOutputDto();
    }


    @Override
    public List<StudentSimpleOutputDto> getAllStudents() {
        return studentRepository.findAll().stream().map(Student::studentToStudentSimpleOutputDto).toList();
    }

    @Override
    public StudentSimpleOutputDto updateStundent(StudentInputDto studentInputDto) {
        Student student = studentRepository.findById(studentInputDto.getId_student()).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID del alumno")
        );

        student.setBranch(studentInputDto.getBranch());
        student.setComments(studentInputDto.getComments());
        student.setNum_hours_week(studentInputDto.getNum_hours_week());

        studentRepository.save(student);

        return student.studentToStudentSimpleOutputDto();
    }

    @Override
    public void addStudentToAsignatura(String id_student, String id_asignatura) {
        Student student = studentRepository.findById(id_student).orElseThrow(
                () -> new EntityNotFoundException("No se ha encontrado la ID del alumno")
        );

        Asignatura asignatura = asignaturaRepository.findById(id_asignatura).orElseThrow(
                () -> new EntityNotFoundException("No se ha encontrado la ID de la asignatura")
        );

        student.getAsignaturas().add(asignatura);
        studentRepository.save(student);
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
        Student student = studentRepository.findById(id_student).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID del alumno")
        );

        List<Asignatura> asignaturas = asignaturaRepository.findAllById(id_asignatura);

        for (Asignatura asignatura : asignaturas) {
            asignatura.getStudents().add(student);
        }


        asignaturaRepository.saveAll(asignaturas);

    }

    @Override
    public void desasignarAsignaturasEstudiante(String id_student, List<String> id_asignatura) {
        Student student = studentRepository.findById(id_student).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID del alumno")
        );

        List<Asignatura> asignaturas = asignaturaRepository.findAllById(id_asignatura);

        for (Asignatura asignatura : asignaturas) {
            asignatura.getStudents().remove(student);
        }

        asignaturaRepository.saveAll(asignaturas);

    }

    @Override
    public Object devuelveAlu(String id, String parametro) {
            switch (parametro){
                case "simple":
                    return studentRepository.findById(id).orElseThrow(
                            () -> new EntityNotFoundException("No se encuentra la ID del alumno")
                    ).studentToStudentSimpleOutputDto();
                case "full":
                    return studentRepository.findById(id).orElseThrow(
                            () -> new EntityNotFoundException("No se encuentra la ID del alumno")
                    ).studentToStudentOutputDto();
                default:
                    return new UnprocessableEntityException("Los parametros son incorrectos");
            }
    }
}