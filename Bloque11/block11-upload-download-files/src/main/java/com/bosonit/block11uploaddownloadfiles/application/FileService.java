package com.bosonit.block11uploaddownloadfiles.application;

import com.bosonit.block11uploaddownloadfiles.controller.dto.FileOutputDto;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileService {



    FileOutputDto addUpdateFile(MultipartFile file, String category) throws IOException;

    Resource downloadFileById(Long id);

    Resource downloadFileByName(String name);

    void updatePath(String path);
}
