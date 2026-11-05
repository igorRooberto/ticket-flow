package com.igor.Ticket_flow.services;

import com.igor.Ticket_flow.domain.entities.Reservation;
import com.igor.Ticket_flow.domain.entities.TicketLot;
import com.igor.Ticket_flow.domain.enums.ReservationStatus;
import com.igor.Ticket_flow.repositories.ReservationRepository;
import com.igor.Ticket_flow.repositories.TicketLotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationExpirationScheduler {

    private final ReservationRepository reservationRepository;

    @Transactional
    @Scheduled(fixedRate = 300_000)
    public void expireReservations() {

        List<Reservation> reservations = reservationRepository.findExpiredReservations(ReservationStatus.PENDING, Instant.now());

        if(reservations.isEmpty()){
            return;
        }

        for(Reservation reservation : reservations){
            reservation.expiredReservation();

            TicketLot ticketLot = reservation.getTicketLot();
            ticketLot.increaseTicketsAvailable(reservation.getQuantity());
        }
    }

}
