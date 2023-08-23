package com.bosonit.block13mongodb.controller;


import com.bosonit.block13mongodb.application.PersonaServiceImpl;
import com.bosonit.block13mongodb.controller.dto.PersonInputDto;
import com.bosonit.block13mongodb.controller.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")

public class ControllerPersona {

    @Autowired
    PersonaServiceImpl personaService;

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto personInputDto){
        return ResponseEntity.ok().body(personaService.addPerson(personInputDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById(@PathVariable String id){
        return ResponseEntity.ok().body(personaService.getPersonById(id));
    }

    @GetMapping
    public Page<PersonOutputDto> getPersons(@RequestParam (defaultValue = "1", required = true) int pageNumber,
                                            @RequestParam (defaultValue = "10") int pageSize){
        return personaService.getAllPersons(PageRequest.of(pageNumber - 1, pageSize));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonById(@PathVariable String id){
        personaService.deletePersona(id);
        return ResponseEntity.ok().body("La persona con ID: " + id + " ha sido eliminada con exito");
    }

    @PutMapping
    public ResponseEntity<PersonOutputDto> updatePerson(@RequestBody PersonInputDto personInputDto){
        return ResponseEntity.ok().body(personaService.updatePerson(personInputDto));
    }
}
