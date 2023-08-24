package com.Bosonit.block7crudvalidation2.application.Teacher;

import com.Bosonit.block7crudvalidation2.application.Teacher.TeacherService;
import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Person;
import com.Bosonit.block7crudvalidation2.domain.Student;
import com.Bosonit.block7crudvalidation2.domain.Teacher;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.repository.PersonRepository;
import com.Bosonit.block7crudvalidation2.repository.StudentRepository;
import com.Bosonit.block7crudvalidation2.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TeacherServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private TeacherService teacherService = new TeacherServiceImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTeacher() {
        String personId = "1";
        TeacherInputDto teacherInputDto = new TeacherInputDto();
        teacherInputDto.setId_persona(personId);
        Person person = new Person();
        when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        Teacher savedTeacher = new Teacher();
        savedTeacher.setPerson(person);
        when(teacherRepository.save(any(Teacher.class))).thenReturn(savedTeacher);

        TeacherOutputDto result = teacherService.addTeacher(teacherInputDto);

        assertNotNull(result);
        verify(teacherRepository, times(1)).save(any(Teacher.class));
        verify(personRepository, times(1)).findById(personId);
    }

    @Test
    public void testGetTeacherById() {
        String teacherId = "2";
        Person person = new Person();
        Teacher teacher = new Teacher();
        teacher.setPerson(person);
        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));

        TeacherOutputDto result = teacherService.getTeacherById(teacherId);

        assertNotNull(result);
        verify(teacherRepository, times(1)).findById(teacherId);
    }

    @Test
    public void testGetAllTeacher(){
        Person person = new Person();
        person.setId_persona("personId");

        Teacher teacher = new Teacher();
        teacher.setPerson(person);

        Teacher teacher1 = new Teacher();
        teacher1.setPerson(person);

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        teachers.add(teacher1);

        when(teacherRepository.findAll()).thenReturn(teachers);

        List<TeacherOutputDto> result = teacherService.getAllTeachers();

        assertEquals(2, result.size());

    }

    @Test
    public void testUpdateTeacher(){
        TeacherInputDto teacherInputDto = new TeacherInputDto();
        teacherInputDto.setId_profesor("id_profesor");
        teacherInputDto.setBranch("aaa");
        teacherInputDto.setComments("ppp");

        Person person = new Person();
        Teacher teacher = new Teacher(teacherInputDto);
        teacher.setPerson(person);

        when(teacherRepository.findById("id_profesor")).thenReturn(Optional.of(teacher));
        when(teacherRepository.save(any())).thenReturn(teacher);

        TeacherOutputDto result = teacherService.updateTeacher(teacherInputDto);

        assertNotNull(result);
        assertEquals("ppp", teacher.getComments());
    }

    @Test
    public void testAddStudentToTeacher() {
        String studentId = "1";
        String teacherId = "2";
        Student student = new Student();
        Teacher teacher = new Teacher();

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));

        teacherService.addStudentToTeacher(studentId, teacherId);

        verify(studentRepository, times(1)).findById(studentId);
        verify(teacherRepository, times(1)).findById(teacherId);
        verify(studentRepository, times(1)).save(any(Student.class));
        verify(teacherRepository, times(1)).save(any(Teacher.class));

    }

    @Test
    public void testAddTeacherThrowsEntityNotFoundException() {
        String invalidId = "invalid_id";
        TeacherInputDto teacherInputDto = new TeacherInputDto();
        teacherInputDto.setId_persona(invalidId);

        when(personRepository.findById(invalidId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            teacherService.addTeacher(teacherInputDto);
        });

        assertEquals("No se encuentra la ID de persona", exception.getMessage());
    }

    @Test
    public void testGetTeacherByIdThrowsEntityNotFoundException() {
        String invalidId = "invalid_id";

        when(teacherRepository.findById(invalidId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            teacherService.getTeacherById(invalidId);
        });

        assertEquals("No se encuentra la ID", exception.getMessage());
    }

    @Test
    public void testUpdateTeacherThrowsEntityNotFoundException() {
        String invalidId = "invalid_id";

        TeacherInputDto teacherInputDto = new TeacherInputDto();
        teacherInputDto.setId_profesor(invalidId);

        when(teacherRepository.findById(invalidId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            teacherService.updateTeacher(teacherInputDto);
        });

        assertEquals("No se encuentra la ID", exception.getMessage());
    }





}
