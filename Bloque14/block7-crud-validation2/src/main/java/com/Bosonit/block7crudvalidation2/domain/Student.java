package com.Bosonit.block7crudvalidation2.domain;

import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentFullOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentSimpleOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id_student;
    int num_hours_week;
    String comments;
    String branch;

    @OneToOne
    @JoinColumn(name = "id_persona", nullable = false, unique = true)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Teacher teacher;

    @ManyToMany(mappedBy = "students")
    Set<Asignatura> asignaturas;

    public Student(StudentInputDto studentInputDto){
        this.num_hours_week = studentInputDto.getNum_hours_week();
        this.comments = studentInputDto.getComments();
        this.branch = studentInputDto.getBranch();
    }

    public StudentFullOutputDto studentToStudentOutputDto(){
        return new StudentFullOutputDto(
                this.id_student,
                this.num_hours_week,
                this.comments,
                this.branch,
                this.person.getId_persona(),
                this.person.getUsername(),
                this.person.getPassword(),
                this.person.getName(),
                this.person.getSurname(),
                this.person.getCompany_email(),
                this.person.getPersonal_email(),
                this.person.getCity(),
                this.person.isActivate(),
                this.person.getCreated_date(),
                this.person.getImagen_url(),
                this.person.getTermination_date()
        );
    }

    public StudentSimpleOutputDto studentToStudentSimpleOutputDto(){
        String teacherId = this.teacher != null ? this.teacher.getId_profesor() : null;
        return new StudentSimpleOutputDto(
                this.id_student,
                this.person.id_persona,
                this.num_hours_week,
                this.comments,
                teacherId,
                this.branch
        );
    }
}