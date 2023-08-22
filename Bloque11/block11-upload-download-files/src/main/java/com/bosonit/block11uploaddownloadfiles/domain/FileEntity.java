package com.bosonit.block11uploaddownloadfiles.domain;

import com.bosonit.block11uploaddownloadfiles.controller.dto.FileInputDto;
import com.bosonit.block11uploaddownloadfiles.controller.dto.FileOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FileEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String fileName;
    private Date uploadDate;
    private String category;

    public FileEntity(FileInputDto fileInputDto){
        this.fileName = fileInputDto.getFileName();
        this.uploadDate = fileInputDto.getUploadDate();
        this.category = fileInputDto.getCategory();
    }

    public FileOutputDto fileToFileOutputDto(){
        return new FileOutputDto(
                this.id,
                this.fileName,
                this.uploadDate,
                this.category
        );
    }
}
