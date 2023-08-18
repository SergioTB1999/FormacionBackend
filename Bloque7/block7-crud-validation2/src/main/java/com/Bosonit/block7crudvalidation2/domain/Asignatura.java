package com.Bosonit.block7crudvalidation2.domain;

import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    public Asignatura(AsignaturaInputDto subjectInputDto){
        this.asignatura = subjectInputDto.getAsignatura();
        this.comment = subjectInputDto.getComment();
        this.initialDate = subjectInputDto.getInitialDate();
        this.finishDate = subjectInputDto.getFinishDate();
    }

    public AsignaturaOutputDto subjectToSubjectOutputDto(){
        String id_student = this.student != null ? this.student.id_student : null;
        return new AsignaturaOutputDto(
                this.id_asignatura,
                id_student,
                this.asignatura,
                this.comment,
                this.initialDate,
                this.finishDate
        );
    }

}