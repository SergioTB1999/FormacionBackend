package com.bosonit.block10dockerizeapp.domain;

import com.bosonit.block10dockerizeapp.controller.dto.PersonInputDto;
import com.bosonit.block10dockerizeapp.controller.dto.PersonOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Person {

    @Id
    @GeneratedValue
    @SequenceGenerator(name = "person_seq_gen", sequenceName = "person_seq", allocationSize = 1)
    private int id;
    private String name;
    private String password;

    public Person(PersonInputDto personInputDto){
        this.name = personInputDto.getName();
        this.password = personInputDto.getPassword();
    }

    public PersonOutputDto personToPersonOutputDto(){
        return new PersonOutputDto(
                this.id,
                this.name,
                this.password
        );
    }
}
