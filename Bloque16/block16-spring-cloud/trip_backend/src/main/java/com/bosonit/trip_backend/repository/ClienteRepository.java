package com.bosonit.trip_backend.repository;

import com.bosonit.trip_backend.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity,Long> {
}
