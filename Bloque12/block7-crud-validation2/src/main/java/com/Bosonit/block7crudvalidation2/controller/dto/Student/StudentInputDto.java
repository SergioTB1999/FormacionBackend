package com.Bosonit.block7crudvalidation2.controller.dto.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInputDto {

    String id_student;
    String id_persona;
    int num_hours_week;
    String comments;
    String id_profesor;
    String branch;

}
