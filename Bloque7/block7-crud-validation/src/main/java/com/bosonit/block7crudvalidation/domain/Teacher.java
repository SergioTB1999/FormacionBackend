package com.bosonit.block7crudvalidation.domain;


import com.bosonit.block7crudvalidation.controller.dto.Teacher.TeacherInputDto;
import com.bosonit.block7crudvalidation.controller.dto.Teacher.TeacherOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id_profesor;
    String comments;
    String branch;

    @OneToOne
    @JoinColumn(name = "id_persona", nullable = false, unique = true)
    private Person person;

    @OneToMany(mappedBy = "teacher")
    private Set<Student> students;

    public Teacher(TeacherInputDto teacherInputDto){
        this.comments = teacherInputDto.getComments();
        this.branch = teacherInputDto.getBranch();
    }

    public TeacherOutputDto teacherToTeacherOutputDto(){
        return new TeacherOutputDto(
               this.id_profesor,
               this.comments,
               this.branch,
                this.person.getId_persona(),
                this.person.getUsuario(),
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

}
