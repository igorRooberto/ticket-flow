package com.igor.Ticket_flow.repositories;

import com.igor.Ticket_flow.domain.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT event FROM Event event LEFT JOIN FETCH event.ticketLots WHERE event.id = :eventId")
    Optional<Event> findByWithTicketsLots(@Param("eventId") Long eventId);
}
