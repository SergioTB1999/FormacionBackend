package com.bosonit.FeignProject.controller.dto.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TeacherOutputDto {

    String id_profesor;
    String id_persona;
    String comments;
    String branch;
}
