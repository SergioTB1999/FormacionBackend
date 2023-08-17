package com.Bosonit.block7crudvalidation2.domain;

import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonStudentOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonTeacherOutputDto;
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

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    @OneToOne(cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    private Teacher teacher;



    public Person(PersonInputDto personInputDto){
        this.usuario = personInputDto.getUsuario();
        this.password = personInputDto.getPassword();
        this.name = personInputDto.getName();
        this.surname = personInputDto.getSurname();
        this.company_email = personInputDto.getCompany_email();
        this.personal_email = personInputDto.getPersonal_email();
        this.city = personInputDto.getCity();
        this.activate = personInputDto.isActivate();
        this.created_date = personInputDto.getCreated_date();
        this.imagen_url = personInputDto.getImagen_url();
        this.termination_date = personInputDto.getTermination_date();
    }

    public PersonOutputDto personToPersonOutputDto(){
        return new PersonOutputDto(
                this.id_persona,
                this.usuario,
                this.password,
                this.name,
                this.surname,
                this.company_email,
                this.personal_email,
                this.city,
                this.activate,
                this.created_date,
                this.imagen_url,
                this.termination_date
        );
    }

    public PersonStudentOutputDto personStudentToPersonStudentOutputDto(){
        return new PersonStudentOutputDto(
                this.id_persona,
                this.usuario,
                this.password,
                this.name,
                this.surname,
                this.company_email,
                this.personal_email,
                this.city,
                this.activate,
                this.created_date,
                this.imagen_url,
                this.termination_date,
                this.student.getId_student(),
                this.student.getNum_hours_week(),
                this.student.getComments(),
                this.student.getBranch()
        );
    }

    public PersonTeacherOutputDto personTeacherToPersonTeacherOutputDto(){
        return new PersonTeacherOutputDto(
                this.id_persona,
                this.usuario,
                this.password,
                this.name,
                this.surname,
                this.company_email,
                this.personal_email,
                this.city,
                this.activate,
                this.created_date,
                this.imagen_url,
                this.termination_date,
                this.teacher.getId_profesor(),
                this.teacher.getComments(),
                this.teacher.getBranch()
        );
    }

}