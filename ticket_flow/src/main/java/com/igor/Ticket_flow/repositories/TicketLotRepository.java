package com.igor.Ticket_flow.repositories;

import com.igor.Ticket_flow.domain.entities.TicketLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketLotRepository extends JpaRepository<TicketLot, Long> {

    List<TicketLot> findByEventId(Long eventId);
}
