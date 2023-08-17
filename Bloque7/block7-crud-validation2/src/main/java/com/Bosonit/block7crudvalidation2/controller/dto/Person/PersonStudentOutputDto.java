package com.Bosonit.block7crudvalidation2.controller.dto.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PersonStudentOutputDto {

    String id_persona;
    String usuario;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    boolean activate;
    Date created_date;
    String imagen_url;
    Date termination_date;
    String id_estudiante;
    int num_hours_week;
    String comments;
    String branch;

}
