package com.bosonit.trip_frontend.repository;

import com.bosonit.trip_frontend.model.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity,Long> {
}
