package com.Bosonit.block7crudvalidation2.Feign;

import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TeacherService", url = "http://localhost:8081")
public interface TeacherFeignClient {

    @GetMapping("/profesor/{id}")
    TeacherOutputDto getProfesorById(@PathVariable String id);
}
