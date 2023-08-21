package com.bosonit.FeignProject.controller.dto.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TeacherInputDto {

    String id_profesor;
    String comments;
    String branch;
    String id_persona;
}
