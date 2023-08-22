package com.bosonit.block11uploaddownloadfiles.repository;

import com.bosonit.block11uploaddownloadfiles.controller.dto.FileOutputDto;
import com.bosonit.block11uploaddownloadfiles.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    Optional<FileEntity> findByFileName(String fileName);
}
