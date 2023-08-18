package com.Bosonit.block7crudvalidation2.application.Person;

import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonStudentOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonTeacherOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Person;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.repository.PersonRepository;
import com.Bosonit.block7crudvalidation2.repository.StudentRepository;
import com.Bosonit.block7crudvalidation2.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public PersonOutputDto addPerson(PersonInputDto person){
        return personRepository.save(new Person(person)).personToPersonOutputDto();
    }


    @Override
    public Iterable<PersonOutputDto> findPersonByName(String name) {
        return personRepository.findAll().stream().filter(
                person -> person.getName().equals(name)
        ).map(Person::personToPersonOutputDto).toList();
    }

    @Override
    public PersonTeacherOutputDto findPersonTeacherById(String id) {
        Person person = personRepository.findById(id).orElseThrow();

        if (person.getTeacher() != null) return person.personTeacherToPersonTeacherOutputDto();
        else return null;
    }

    @Override
    public PersonStudentOutputDto findePersonStudentById(String id) {
        Person person = personRepository.findById(id).orElseThrow();
        if (person.getStudent() != null) return person.personStudentToPersonStudentOutputDto();
        else return null;
    }

    @Override
    public PersonOutputDto findPersonById(String id) {
        return personRepository.findById(id).orElseThrow().personToPersonOutputDto();
    }


    @Override
    public Iterable<PersonOutputDto> getAllPersons() {
        return personRepository.findAll()
                .stream()
                .map(Person::personToPersonOutputDto).toList();
    }


    @Override
    public PersonOutputDto updatePerson(PersonInputDto personInputDto) {
        Person person = personRepository.findById(personInputDto.getId_persona()).orElseThrow();

        person.setUsuario(personInputDto.getUsuario());
        person.setPassword(personInputDto.getPassword());
        person.setName(personInputDto.getName());
        person.setSurname(personInputDto.getSurname());
        person.setCompany_email(personInputDto.getCompany_email());
        person.setPersonal_email(personInputDto.getPersonal_email());
        person.setCity(personInputDto.getCity());
        person.setActivate(personInputDto.isActivate());
        person.setCreated_date(personInputDto.getCreated_date());
        person.setImagen_url(personInputDto.getImagen_url());
        person.setTermination_date(personInputDto.getTermination_date());

        personRepository.save(person);

        return person.personToPersonOutputDto();
    }

    @Override
    public void deletePersonById(String id) {
        personRepository.findById(id)
                .orElseThrow();
        personRepository.deleteById(id);
    }
}