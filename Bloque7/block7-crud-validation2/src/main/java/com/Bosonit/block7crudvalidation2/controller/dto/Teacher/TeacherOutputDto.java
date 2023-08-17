package com.Bosonit.block7crudvalidation2.controller.dto.Teacher;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TeacherOutputDto {

    String id_profesor;
    String comments;
    String branch;
}
