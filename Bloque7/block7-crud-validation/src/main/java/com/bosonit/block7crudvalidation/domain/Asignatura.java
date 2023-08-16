package com.bosonit.block7crudvalidation.domain;

import com.bosonit.block7crudvalidation.controller.dto.Asignatura.AsignaturaInputDto;
import com.bosonit.block7crudvalidation.controller.dto.Asignatura.AsignaturaOutputDto;
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
        return new AsignaturaOutputDto(
                this.id_asignatura,
                this.asignatura,
                this.comment,
                this.initialDate,
                this.finishDate
        );
    }

}
