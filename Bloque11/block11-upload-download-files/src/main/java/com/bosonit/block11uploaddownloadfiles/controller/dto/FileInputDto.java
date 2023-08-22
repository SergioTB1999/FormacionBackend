package com.bosonit.block11uploaddownloadfiles.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FileInputDto {

    long id;
    String FileName;
    Date uploadDate;
    String category;
}
