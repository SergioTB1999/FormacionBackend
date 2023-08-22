package com.bosonit.block11uploaddownloadfiles.application;

import com.bosonit.block11uploaddownloadfiles.configuration.FileProperties;
import com.bosonit.block11uploaddownloadfiles.controller.dto.FileOutputDto;
import com.bosonit.block11uploaddownloadfiles.domain.FileEntity;
import com.bosonit.block11uploaddownloadfiles.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

@Service

public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    FileProperties fileProperties;

    @Override
    public FileOutputDto addUpdateFile(MultipartFile file, String category) throws IOException {

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setUploadDate(new Date());
        fileEntity.setCategory(category);

        File savedFile = new File(fileProperties.getLocation(), file.getOriginalFilename());
        file.transferTo(savedFile);

        return fileRepository.save(fileEntity).fileToFileOutputDto();
    }

    @Override
    public Resource downloadFileById(Long id) {
        FileEntity optionalFileEntity = fileRepository.findById(id).orElseThrow();
        File file = new File(fileProperties.getLocation(), optionalFileEntity.getFileName());
        Resource resource = new FileSystemResource(file);
        return resource;
    }

    @Override
    public Resource downloadFileByName(String name) {
        FileEntity optionalFileEntity = fileRepository.findByFileName(name).orElseThrow();
        File file = new File(fileProperties.getLocation(), optionalFileEntity.getFileName());
        Resource resource = new FileSystemResource(file);
        return resource;
    }

    @Override
    public void updatePath(String path) {
        fileProperties.setLocation(path);
    }


}
