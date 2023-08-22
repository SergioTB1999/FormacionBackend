package com.Bosonit.block7crudvalidation2.controller;

import com.Bosonit.block7crudvalidation2.Feign.TeacherFeignClient;
import com.Bosonit.block7crudvalidation2.application.Person.PersonServiceImpl;
import com.Bosonit.block7crudvalidation2.application.Student.StudentServiceImpl;
import com.Bosonit.block7crudvalidation2.application.Teacher.TeacherServiceImpl;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonStudentOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonTeacherOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Teacher.TeacherOutputDto;
import com.Bosonit.block7crudvalidation2.errors.CustomError;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.errors.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController

@RequestMapping("/persona")

public class Controller {

    @Autowired
    PersonServiceImpl personService;

    @Autowired
    TeacherFeignClient teacherFeignClient;

    @ExceptionHandler
    @ResponseStatus
    public CustomError handleUnprocessableEntityException(UnprocessableEntityException ex){
        return new CustomError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus
    public CustomError handleEntityNotFoundException (EntityNotFoundException ex){
        return new CustomError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }

    @PostMapping
    public ResponseEntity<?> addPerson(@RequestBody PersonInputDto person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.addPerson(person));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(
            @PathVariable String id,
            @RequestParam(defaultValue = "simple", name = "outputType") String param){
        return ResponseEntity.ok().body(personService.getPerson(id, param));
    }

    @GetMapping("/profesor/{id}")
    public ResponseEntity<TeacherOutputDto> getTeacherById(
            @PathVariable String id){
        try{
            TeacherOutputDto teacherOutputDto = teacherFeignClient.getProfesorById(id);

            if (teacherOutputDto != null) return ResponseEntity.ok().body(teacherOutputDto);
            else throw new EntityNotFoundException("No se encuentra al profesor");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping()
    public Iterable<PersonOutputDto> getPersons(){
        return personService.getAllPersons();
    }

    @PutMapping()
    public ResponseEntity<PersonOutputDto> updatePerson(@RequestBody PersonInputDto personInputDto){
            return ResponseEntity.ok().body(personService.updatePerson(personInputDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable String id){
            return ResponseEntity.ok().body("La persona con el ID " + id + " se ha borrado correctamente");
    }

}