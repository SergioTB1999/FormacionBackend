package com.bosonit.trip_backend.repository;

import com.bosonit.trip_backend.model.ViajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViajeRepository extends JpaRepository<ViajeEntity, Long> {
}
