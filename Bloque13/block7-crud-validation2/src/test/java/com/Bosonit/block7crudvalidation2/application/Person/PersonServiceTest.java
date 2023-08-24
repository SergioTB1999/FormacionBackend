package com.Bosonit.block7crudvalidation2.application.Person;

import com.Bosonit.block7crudvalidation2.application.Person.PersonService;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonStudentOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonTeacherOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Person;
import com.Bosonit.block7crudvalidation2.domain.Student;
import com.Bosonit.block7crudvalidation2.domain.Teacher;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.errors.UnprocessableEntityException;
import com.Bosonit.block7crudvalidation2.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService = new PersonServiceImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPerson() {
        PersonInputDto inputDto = new PersonInputDto();
        inputDto.setUsuario("username");

        Person person = new Person();
        person.setUsuario("username");

        when(personRepository.save(any())).thenReturn(person);

        PersonOutputDto result = personService.addPerson(inputDto);

        assertNotNull(result);
        assertEquals("username", result.getUsuario());
    }

    @Test
    public void testFindPersonByName() {
        Person person = new Person();
        person.setName("John");

        when(personRepository.findAll()).thenReturn(Collections.singletonList(person));


        Iterable<PersonOutputDto> result = personService.findPersonByName("John");

        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
        assertEquals("John", result.iterator().next().getName());
    }

    @Test
    public void testFindPersonById() {
        Person person = new Person();
        person.setId_persona("1");

        when(personRepository.findById("1")).thenReturn(Optional.of(person));

        PersonOutputDto result = personService.findPersonById("1");

        assertNotNull(result);
        assertEquals("1", result.getId_persona());
    }

    @Test
    public void testFindPersonById_EntityNotFoundException(){
        String id = "123";
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> personService.findPersonById(id)
        );

        assertEquals("No se encuentra la ID de la persona", exception.getMessage());
    }

    @Test
    public void testGetAllPersons() {
        // Arrange
        Person person1 = new Person();
        person1.setId_persona("1");
        person1.setName("pepe");

        Person person2 = new Person();
        person2.setId_persona("2");
        person2.setName("manuel");

        List<Person> persons = new ArrayList<>();

        persons.add(person1);
        persons.add(person2);

        when(personRepository.findAll()).thenReturn(persons);

        // Act
        Iterable<PersonOutputDto> result = personService.getAllPersons();

        // Assert
        assertEquals(2, ((List<PersonOutputDto>) result).size());
        assertEquals("1", ((List<PersonOutputDto>) result).get(0).getId_persona());
        assertEquals("pepe", ((List<PersonOutputDto>) result).get(0).getName());
        assertEquals("2", ((List<PersonOutputDto>) result).get(1).getId_persona());
        assertEquals("manuel", ((List<PersonOutputDto>) result).get(1).getName());

        // Verify
        verify(personRepository, times(1)).findAll();
    }

    @Test
    public void testUpdatePerson() {
        // Arrange
        PersonInputDto personInputDto = new PersonInputDto();
        personInputDto.setId_persona("1");
        personInputDto.setName("pepe");
        personInputDto.setCity("cuenca");

        Person existingPerson = new Person();
        existingPerson.setId_persona("1");

        when(personRepository.findById("1")).thenReturn(java.util.Optional.of(existingPerson));
        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> invocation.getArgument(0));


        PersonOutputDto updatedPerson = personService.updatePerson(personInputDto);

        assertNotNull(updatedPerson);
        assertEquals("1", updatedPerson.getId_persona());
        assertEquals("pepe", updatedPerson.getName());
        assertEquals("cuenca", updatedPerson.getCity());

        verify(personRepository, times(1)).findById("1");
        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    public void testUpdatePersona_EntityNotFoundException(){
        PersonInputDto personInputDto = new PersonInputDto();
        personInputDto.setId_persona("123");
        personInputDto.setUsuario("aaa");
        personInputDto.setName("pepe");

        when(personRepository.findById(personInputDto.getId_persona())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> personService.updatePerson(personInputDto)
        );

        assertEquals("No se encuentra la ID de la persona", exception.getMessage());
        verify(personRepository, never()).save(any());
    }

    @Test
    public void testDeleteExistingPersonById() {
        String existingPersonId = "1";
        Person existingPerson = new Person();
        when(personRepository.findById(existingPersonId)).thenReturn(Optional.of(existingPerson));

        personService.deletePersonById(existingPersonId);

        verify(personRepository, times(1)).findById(existingPersonId);
        verify(personRepository, times(1)).deleteById(existingPersonId);
    }

    @Test
    public void testDeletePersonaById_EntityNotFoundException(){
        String personaId = "123";
        Person person = new Person();

        when(personRepository.findById(personaId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> personService.deletePersonById(personaId)
        );

        assertEquals("No se encuentra la ID de la persona", exception.getMessage());
    }

    @Test
    public void testGetPersonSimple() {
        // Arrange
        String personId = "1";
        Person person = new Person();
        when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        // Act
        Object result = personService.getPerson(personId, "simple");

        // Assert
        assertNotNull(result);
        assertTrue(result instanceof PersonOutputDto);
        verify(personRepository, times(1)).findById(personId);
    }

    @Test
    void testGetPerson_EntityNotFoundException() {
        String id = "123";
        String param = "simple";

        when(personRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> personService.getPerson(id, param)
        );

        assertEquals("No se encuentra la ID de la persona", exception.getMessage());
        verify(personRepository, never()).save(any());
    }


    @Test
    public void testGetPersonFullTeacher() {
        // Arrange
        String personId = "2";
        Person person = new Person();
        person.setTeacher(new Teacher()); // Assuming you have a Teacher class
        when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        // Act
        Object result = personService.getPerson(personId, "full");

        // Assert
        assertNotNull(result);
        assertTrue(result instanceof PersonTeacherOutputDto); // Assuming you have a PersonTeacherOutputDto class
        verify(personRepository, times(1)).findById(personId);
    }

    @Test
    public void testGetPersonFullStudent() {
        // Arrange
        String personId = "3";
        Person person = new Person();
        person.setStudent(new Student()); // Assuming you have a Student class
        when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        // Act
        Object result = personService.getPerson(personId, "full");

        // Assert
        assertNotNull(result);
        assertTrue(result instanceof PersonStudentOutputDto); // Assuming you have a PersonStudentOutputDto class
        verify(personRepository, times(1)).findById(personId);
    }

}
