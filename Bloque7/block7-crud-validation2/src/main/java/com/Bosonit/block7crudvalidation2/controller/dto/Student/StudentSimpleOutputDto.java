package com.Bosonit.block7crudvalidation2.controller.dto.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentSimpleOutputDto {

    String id_student;
    int num_hours_week;
    String comments;
    String branch;
}
