package com.igor.Ticket_flow.services;

import com.igor.Ticket_flow.domain.entities.Event;
import com.igor.Ticket_flow.domain.entities.TicketLot;
import com.igor.Ticket_flow.exceptions.BadRequestException;
import com.igor.Ticket_flow.exceptions.NotFoundException;
import com.igor.Ticket_flow.repositories.EventRepository;
import com.igor.Ticket_flow.repositories.TicketLotRepository;
import com.igor.Ticket_flow.view.dto.request.CreateTicketLotRequestDto;
import com.igor.Ticket_flow.view.dto.response.TicketLotResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateTicketLotUseCase {

    private final TicketLotRepository ticketLotRepository;
    private final EventRepository eventRepository;

    public TicketLotResponseDto execute(CreateTicketLotRequestDto dto){

        if(dto.salesStartAt().isBefore(LocalDateTime.now())){
            throw new BadRequestException("Data De Inicio Inválida");
        }

        if(dto.salesStartAt().isAfter(dto.salesEndAt())){
            throw new BadRequestException("Data De Inicio Inválida");
        }

         Event event = eventRepository.findById(dto.eventId())
                .orElseThrow(() -> new NotFoundException("Evento Não Encontrado"));

        TicketLot ticketLot = TicketLot.builder()
                .name(dto.name())
                .price(dto.price())
                .totalQuantity(dto.totalQuantity())
                .availableQuantity(dto.totalQuantity())
                .salesStartAt(dto.salesStartAt())
                .salesEndAt(dto.salesEndAt())
                .event(event)
                .build();

        TicketLot ticketLotSaved = ticketLotRepository.save(ticketLot);

        return new TicketLotResponseDto(
                ticketLotSaved.getId(),
                ticketLotSaved.getName(),
                ticketLot.getPrice(),
                ticketLotSaved.getTotalQuantity(),
                ticketLotSaved.getAvailableQuantity(),
                ticketLotSaved.getSalesStartAt(),
                ticketLot.getSalesEndAt()
        );
    }
}
