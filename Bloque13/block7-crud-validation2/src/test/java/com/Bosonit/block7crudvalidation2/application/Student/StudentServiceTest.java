package com.Bosonit.block7crudvalidation2.application.Student;

import com.Bosonit.block7crudvalidation2.controller.dto.Asignatura.AsignaturaOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentFullOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Student.StudentSimpleOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Asignatura;
import com.Bosonit.block7crudvalidation2.domain.Person;
import com.Bosonit.block7crudvalidation2.domain.Student;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.repository.AsignaturaRepository;
import com.Bosonit.block7crudvalidation2.repository.PersonRepository;
import com.Bosonit.block7crudvalidation2.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents() {
        Person person = new Person();
        person.setId_persona("personId");

        Student student1 = new Student();
        student1.setPerson(person);

        Student student2 = new Student();
        student2.setPerson(person);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);

        when(studentRepository.findAll()).thenReturn(studentList);

        List<StudentSimpleOutputDto> result = studentService.getAllStudents();

        assertEquals(2, result.size());
    }


    @Test
    void testAddStudent() {
        StudentInputDto studentInputDto = new StudentInputDto();
        studentInputDto.setId_persona("personId");

        Person person = new Person();
        when(personRepository.findById("personId")).thenReturn(java.util.Optional.of(person));

        Student student = new Student(studentInputDto);
        person.setId_persona("personId");
        student.setPerson(person);
        when(studentRepository.save(any())).thenReturn(student);

        studentService.addStudent(studentInputDto);

        assertNotNull(student.getPerson());
        assertEquals("personId", student.getPerson().getId_persona());
    }


    @Test
    void testUpdateStudent() {
        StudentInputDto studentInputDto = new StudentInputDto();
        studentInputDto.setId_student("studentId");
        studentInputDto.setBranch("branch");

        Person person = new Person(); // Create a mock person for the test
        Student student = new Student(studentInputDto);
        student.setPerson(person); // Set the mock person in the student object

        when(studentRepository.findById("studentId")).thenReturn(Optional.of(student));
        when(studentRepository.save(any())).thenReturn(student);

        StudentSimpleOutputDto result = studentService.updateStundent(studentInputDto);

        assertNotNull(result);
        assertEquals("branch", student.getBranch());
    }

    @Test
    void testAddStudentToNonExistentAsignatura() {
        String studentId = "studentId";
        String asignaturaId = "nonExistentId";

        when(studentRepository.findById(studentId)).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                studentService.addStudentToAsignatura(studentId, asignaturaId));
    }

    @Test
    void testAddStudentToAsignatura() {

        Student student = new Student();

        when(studentRepository.findById("studentId")).thenReturn(java.util.Optional.of(student));
        when(asignaturaRepository.findById("asignaturaId")).thenReturn(java.util.Optional.of(new Asignatura()));

        studentService.addStudentToAsignatura("studentId", "asignaturaId");

        verify(asignaturaRepository, times(1)).save(any()); // Verify that save() was called
    }

    @Test
    void testGetAsignaturasByStudentId() {

        Student student = new Student();
        student.setId_student("studentId");

        Asignatura asignatura1 = new Asignatura();
        asignatura1.setId_asignatura("asignaturaId1");

        Asignatura asignatura2 = new Asignatura();
        asignatura2.setId_asignatura("asignaturaId2");

        Set<Asignatura> asignaturas = new HashSet<>();
        asignaturas.add(asignatura1);
        asignaturas.add(asignatura2);

        student.setAsignaturas(asignaturas);

        when(studentRepository.findById("studentId")).thenReturn(java.util.Optional.of(student));


        List<AsignaturaOutputDto> result = studentService.getAsignaturasByStudentId("studentId");

        assertEquals(2, result.size());
    }

    @Test
    void testAsignarAsignaturasEstudiante() {
        Student student = new Student();
        student.setId_student("studentId");

        Asignatura asignatura1 = new Asignatura();
        asignatura1.setId_asignatura("asignaturaId1");

        Asignatura asignatura2 = new Asignatura();
        asignatura2.setId_asignatura("asignaturaId2");

        List<Asignatura> asignaturas = new ArrayList<>();
        asignaturas.add(asignatura1);
        asignaturas.add(asignatura2);

        List<String> id_asignatura = new ArrayList<>();
        id_asignatura.add("asignaturaId1");
        id_asignatura.add("asignaturaId2");

        when(studentRepository.findById("studentId")).thenReturn(java.util.Optional.of(student));
        when(asignaturaRepository.findAllById(id_asignatura)).thenReturn(asignaturas);

        studentService.asignarAsignaturasEstudiante("studentId", id_asignatura);

        verify(asignaturaRepository, times(1)).saveAll(asignaturas);
    }

    @Test
    public void testDesasignarAsignaturasEstudiante() {
        // Crear datos de prueba y mocks
        String studentId = "studentId";
        List<String> asignaturaIds = new ArrayList<>();
        asignaturaIds.add("asignaturaId1");
        asignaturaIds.add("asignaturaId2");

        Student student = new Student();
        List<Asignatura> asignaturas = new ArrayList<>();
        Asignatura asignatura1 = mock(Asignatura.class); // Crear el mock aquí
        Asignatura asignatura2 = mock(Asignatura.class); // Crear el mock aquí
        asignaturas.add(asignatura1);
        asignaturas.add(asignatura2);

        // Configurar mocks para simular comportamientos
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(asignaturaRepository.findAllById(asignaturaIds)).thenReturn(asignaturas);

        // Configurar mock del objeto HashSet en Asignatura
        HashSet<Student> studentsMock1 = mock(HashSet.class);
        HashSet<Student> studentsMock2 = mock(HashSet.class);
        when(asignatura1.getStudents()).thenReturn(studentsMock1);
        when(asignatura2.getStudents()).thenReturn(studentsMock2);

        // Ejecutar el método a probar
        studentService.desasignarAsignaturasEstudiante(studentId, asignaturaIds);

        // Verificar que se llamó a los métodos necesarios
        verify(studentRepository).findById(studentId);
        verify(asignaturaRepository).findAllById(asignaturaIds);

        // Verificar que se llamó a remove en cada asignatura
        verify(studentsMock1).remove(student);
        verify(studentsMock2).remove(student);

        // Verificar que se guardaron las asignaturas
        verify(asignaturaRepository).saveAll(asignaturas);
    }

    @Test
    public void testDevuelveAluSimple(){
        Person person = new Person();
        Student student = new Student();
        student.setPerson(person);
        student.setId_student("1");
        student.setNum_hours_week(2);
        student.setComments("2");
        student.setBranch("aaaaa");

        when(studentRepository.findById("1")).thenReturn(Optional.of(student));

        // Act
        StudentSimpleOutputDto result = (StudentSimpleOutputDto) studentService.devuelveAlu("1", "simple");

        // Assert
        assertNotNull(result);
        assertEquals("1", result.getId_student());
        assertEquals(2, result.getNum_hours_week());
        assertEquals("aaaaa", result.getBranch());
    }

    @Test
    public void testDevuelveAluFull() {
        // Arrange
        Person person = new Person();
        Student student = new Student();
        student.setPerson(person);
        student.setId_student("1");
        student.setNum_hours_week(2);
        student.setComments("2");
        student.setBranch("aaaaa");

        when(studentRepository.findById("1")).thenReturn(Optional.of(student));

        // Act
        StudentFullOutputDto result = (StudentFullOutputDto) studentService.devuelveAlu("1", "full");

        // Assert
        assertNotNull(result);
        assertEquals("1", result.getId_student());
        assertEquals(2, result.getNum_hours_week());
        assertEquals("aaaaa", result.getBranch());
    }


}
