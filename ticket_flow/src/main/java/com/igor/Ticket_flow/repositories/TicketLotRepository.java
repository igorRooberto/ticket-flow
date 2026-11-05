package com.igor.Ticket_flow.repositories;

import com.igor.Ticket_flow.domain.entities.TicketLot;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketLotRepository extends JpaRepository<TicketLot, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM TicketLot t WHERE t.id = :id")
    Optional<TicketLot> findByIdForUpdate(@Param("id") Long id);

    List<TicketLot> findByEventId(Long eventId);
}
