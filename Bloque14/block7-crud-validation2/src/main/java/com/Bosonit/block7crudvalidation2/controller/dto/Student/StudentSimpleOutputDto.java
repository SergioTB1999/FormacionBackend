package com.Bosonit.block7crudvalidation2.controller.dto.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentSimpleOutputDto {

    String id_student;
    String id_persona;
    int num_hours_week;
    String comments;
    String id_profesor;
    String branch;
}
