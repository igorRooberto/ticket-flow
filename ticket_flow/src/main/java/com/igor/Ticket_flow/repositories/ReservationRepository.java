package com.igor.Ticket_flow.repositories;

import com.igor.Ticket_flow.domain.entities.Reservation;
import com.igor.Ticket_flow.domain.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r JOIN FETCH r.ticketLot WHERE r.status = :status AND r.expiresAt <= :now")
    List<Reservation> findExpiredReservations(@Param("status")ReservationStatus status, @Param("now") Instant now);

    @Query("SELECT r FROM Reservation r JOIN FETCH r.ticketLot JOIN FETCH r.customer WHERE r.id = :reservationId")
    Optional<Reservation> findByReservationWithTicketLotAndCustomer(@Param("reservationId") Long reservationId);

}
