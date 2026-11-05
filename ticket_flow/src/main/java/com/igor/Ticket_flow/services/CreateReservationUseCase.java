package com.igor.Ticket_flow.services;

import com.igor.Ticket_flow.domain.entities.Customer;
import com.igor.Ticket_flow.domain.entities.Reservation;
import com.igor.Ticket_flow.domain.entities.TicketLot;
import com.igor.Ticket_flow.domain.enums.ReservationStatus;
import com.igor.Ticket_flow.exceptions.BadRequestException;
import com.igor.Ticket_flow.exceptions.NotFoundException;
import com.igor.Ticket_flow.repositories.CustomerRepository;
import com.igor.Ticket_flow.repositories.ReservationRepository;
import com.igor.Ticket_flow.repositories.TicketLotRepository;
import com.igor.Ticket_flow.view.dto.request.CreateReservationRequestDto;
import com.igor.Ticket_flow.view.dto.response.ReservationResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class CreateReservationUseCase {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final TicketLotRepository ticketLotRepository;

    @Transactional
    public ReservationResponseDto execute(CreateReservationRequestDto dto){

        TicketLot ticketLot = ticketLotRepository.findByIdForUpdate(dto.ticketLotId())
                .orElseThrow(() -> new NotFoundException("Lote não encontrado"));

        int available = ticketLot.getAvailableQuantity();

        if(available == 0){
            throw new BadRequestException("Ingressos Esgotados");
        }

        if(ticketLot.getAvailableQuantity() < dto.quantity()){
            throw new BadRequestException("Quantidade solicitada maior que a quantidade de ingressos disponíveis");
        }

        Customer customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new NotFoundException("Cliente Não Encontrado"));

        Reservation newReservation = Reservation.builder()
                .quantity(dto.quantity())
                .status(ReservationStatus.PENDING)
                .customer(customer)
                .ticketLot(ticketLot)
                .expiresAt(Instant.now().plus(10, ChronoUnit.MINUTES))
                .build();

        ticketLot.decreaseTicketsAvailable(dto.quantity());

        Reservation reservationSaved = reservationRepository.save(newReservation);
        return new ReservationResponseDto(reservationSaved.getId(), reservationSaved.getQuantity(), reservationSaved.getExpiresAt());
    }
}
