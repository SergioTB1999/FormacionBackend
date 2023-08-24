package com.Bosonit.block7crudvalidation2.domain;

import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id_asignatura;
    String asignatura;
    String comment;
    Date initialDate;
    Date finishDate;

    @ManyToMany
    Set<Student> students = new HashSet<>();

    public Asignatura(AsignaturaInputDto subjectInputDto){
        this.students = new HashSet<>();
        this.asignatura = subjectInputDto.getAsignatura();
        this.comment = subjectInputDto.getComment();
        this.initialDate = subjectInputDto.getInitialDate();
        this.finishDate = subjectInputDto.getFinishDate();
    }

    public AsignaturaOutputDto subjectToSubjectOutputDto(){
        return new AsignaturaOutputDto(
                this.id_asignatura,
                this.asignatura,
                this.comment,
                this.initialDate,
                this.finishDate
        );
    }

}