package com.bosonit.FeignProject.repository;

import com.bosonit.FeignProject.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {

}
