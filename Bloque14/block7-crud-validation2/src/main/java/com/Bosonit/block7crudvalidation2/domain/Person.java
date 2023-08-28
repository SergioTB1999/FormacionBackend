package com.Bosonit.block7crudvalidation2.domain;

import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonStudentOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonTeacherOutputDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "persona", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id_persona;
    @Column(nullable = false)
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
    @Enumerated(EnumType.STRING)
    Role role;

    @OneToOne(cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    private Teacher teacher;



    public Person(PersonInputDto personInputDto){
        this.username = personInputDto.getUsername();
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
        this.role = personInputDto.getRole();
    }

    public PersonOutputDto personToPersonOutputDto(){
        return new PersonOutputDto(
                this.id_persona,
                this.username,
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
                this.role
        );
    }

    public PersonStudentOutputDto personStudentToPersonStudentOutputDto(){
        return new PersonStudentOutputDto(
                this.id_persona,
                this.username,
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
                this.username,
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}