package com.Bosonit.block7crudvalidation2.controller;

import com.Bosonit.block7crudvalidation2.application.Person.PersonServiceImpl;
import com.Bosonit.block7crudvalidation2.application.Student.StudentServiceImpl;
import com.Bosonit.block7crudvalidation2.application.Teacher.TeacherServiceImpl;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonStudentOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonTeacherOutputDto;
import com.Bosonit.block7crudvalidation2.errors.UnprocessableEntityException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")

public class Controller {

    @Autowired
    PersonServiceImpl personService;

    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    TeacherServiceImpl teacherService;

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person) {
        if (person.getUsuario() == null) throw new UnprocessableEntityException("El usuario es obligatorio");
        if (person.getUsuario().length() > 10) throw new EntityNotFoundException("El usuario no puede tener mas de 10 caracteres");
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.addPerson(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable String id, @RequestParam(defaultValue = "simple", name = "outputType") String parametros) {
        PersonStudentOutputDto personStudentOutputDto = personService.findePersonStudentById(id);
        PersonTeacherOutputDto personTeacherOutputDto = personService.findPersonTeacherById(id);

        if (personStudentOutputDto != null){
            return switch (parametros) {
                case "simple" -> ResponseEntity.ok().body(personService.findPersonById(id));
                case "full" -> ResponseEntity.ok().body(personService.findePersonStudentById(id));
                default -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            };
        } else if (personTeacherOutputDto != null){
            return switch (parametros) {
                case "simple" -> ResponseEntity.ok().body(personService.findPersonById(id));
                case "full" -> ResponseEntity.ok().body(personService.findPersonTeacherById(id));
                default -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            };
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public Iterable<PersonOutputDto> getPersons(){
        return personService.getAllPersons();
    }

    @PutMapping()
    public ResponseEntity<PersonOutputDto> updatePerson(@RequestBody PersonInputDto personInputDto){
        try{
            personService.findPersonById(personInputDto.getId_persona());
            return ResponseEntity.ok().body(personService.updatePerson(personInputDto));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}