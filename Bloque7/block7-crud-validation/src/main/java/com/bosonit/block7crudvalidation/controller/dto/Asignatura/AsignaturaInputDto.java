package com.bosonit.block7crudvalidation.controller.dto.Asignatura;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AsignaturaInputDto {

    String id_asignatura;
    String asignatura;
    String comment;
    Date initialDate;
    Date finishDate;
}
