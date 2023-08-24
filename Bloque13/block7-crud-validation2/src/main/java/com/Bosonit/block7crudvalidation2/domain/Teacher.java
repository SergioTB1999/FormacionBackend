package com.Bosonit.block7crudvalidation2.domain;

import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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
    private Set<Student> students = new HashSet<>();

    public Teacher(TeacherInputDto teacherInputDto){
        this.students = new HashSet<>();
        this.comments = teacherInputDto.getComments();
        this.branch = teacherInputDto.getBranch();
    }

    public TeacherOutputDto teacherToTeacherOutputDto(){
        return new TeacherOutputDto(
                this.id_profesor,
                this.person.id_persona,
                this.comments,
                this.branch
        );
    }

}