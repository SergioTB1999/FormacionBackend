package com.bosonit.FeignProject.application;

import com.bosonit.FeignProject.controller.dto.Teacher.TeacherInputDto;
import com.bosonit.FeignProject.controller.dto.Teacher.TeacherOutputDto;

public interface TeacherService {

    TeacherOutputDto getTeacherById(String id);

    TeacherOutputDto addTeacher(TeacherInputDto teacherInputDto);
}
