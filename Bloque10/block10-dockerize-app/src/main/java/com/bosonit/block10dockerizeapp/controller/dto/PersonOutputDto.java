package com.bosonit.block10dockerizeapp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PersonOutputDto {
    int id;
    String name;
    String password;
}