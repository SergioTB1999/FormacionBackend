package com.bosonit.block13mongodb.repository;

import com.bosonit.block13mongodb.model.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Persona, String> {
}
