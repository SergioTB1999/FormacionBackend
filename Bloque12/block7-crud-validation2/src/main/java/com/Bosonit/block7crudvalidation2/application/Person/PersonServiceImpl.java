package com.Bosonit.block7crudvalidation2.application.Person;

import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonInputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonStudentOutputDto;
import com.Bosonit.block7crudvalidation2.controller.dto.Person.PersonTeacherOutputDto;
import com.Bosonit.block7crudvalidation2.domain.Person;
import com.Bosonit.block7crudvalidation2.errors.EntityNotFoundException;
import com.Bosonit.block7crudvalidation2.errors.UnprocessableEntityException;
import com.Bosonit.block7crudvalidation2.repository.PersonRepository;
import com.Bosonit.block7crudvalidation2.repository.StudentRepository;
import com.Bosonit.block7crudvalidation2.repository.TeacherRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        if (person.getUsuario() == null) throw new UnprocessableEntityException("Usuario no puede ser nulo");
        if (person.getUsuario().length() > 10) throw new UnprocessableEntityException("Longitud de usuario no puede ser mayor a 10");
        return personRepository.save(new Person(person)).personToPersonOutputDto();
    }


    @Override
    public Iterable<PersonOutputDto> findPersonByName(String name) {
        return personRepository.findAll().stream().filter(
                person -> person.getName().equals(name)
        ).map(Person::personToPersonOutputDto).toList();
    }

    @Override
    public PersonOutputDto findPersonById(String id) {
        return personRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID de la persona")
        ).personToPersonOutputDto();
    }


    @Override
    public Iterable<PersonOutputDto> getAllPersons() {
        return personRepository.findAll()
                .stream()
                .map(Person::personToPersonOutputDto).toList();
    }

    @Override
    public Iterable<PersonOutputDto> getAllPersonsFilter(String user, String name, String surname, Date createdDateFrom, Date createdDateTo, String orderBy) {
        return personRepository.findAll((Specification<Person>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (user != null) predicates.add(criteriaBuilder.like(root.get("usuario"), "%" + user + "%"));
            if (name != null) predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            if (surname != null) predicates.add(criteriaBuilder.like(root.get("surname"), "%" + surname + "%"));
            if (createdDateFrom != null) predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("created_date"), createdDateFrom));
            if (createdDateTo != null) predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("ceated_date"), createdDateTo));

            criteriaQuery.where(predicates.toArray(new Predicate[0]));

            if (orderBy.equals("user")) criteriaQuery.orderBy(criteriaBuilder.asc(root.get("usuario")));
            else if (orderBy.equals("name")) criteriaQuery.orderBy(criteriaBuilder.asc(root.get("name")));

            return criteriaQuery.getRestriction();
        }).stream().map(Person::personToPersonOutputDto).toList();
    }


    @Override
    public PersonOutputDto updatePerson(PersonInputDto personInputDto) {
        Person person = personRepository.findById(personInputDto.getId_persona()).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID de la persona")
        );

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
                .orElseThrow(
                        () -> new EntityNotFoundException("No se encuentra la ID de la persona")
                );
        personRepository.deleteById(id);
    }

    @Override
    public Object getPerson(String id, String param) {
        Person person = personRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No se encuentra la ID de la persona")
        );

        switch (param) {
            case "simple" -> {
                return person.personToPersonOutputDto();
            }
            case "full" -> {
                if (person.getTeacher() != null) return person.personTeacherToPersonTeacherOutputDto();
                if (person.getStudent() != null) return person.personStudentToPersonStudentOutputDto();
                return new UnprocessableEntityException("La persona no es ni profesor ni estudiante");
            }
            default -> {
                return new UnprocessableEntityException("Los parametros son incorrectos");
            }
        }
    }
}