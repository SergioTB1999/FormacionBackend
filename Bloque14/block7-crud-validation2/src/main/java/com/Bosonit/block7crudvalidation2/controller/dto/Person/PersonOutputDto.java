package com.Bosonit.block7crudvalidation2.controller.dto.Person;

import com.Bosonit.block7crudvalidation2.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonOutputDto {

    String id_persona;
    String username;
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
    Role role;
}
