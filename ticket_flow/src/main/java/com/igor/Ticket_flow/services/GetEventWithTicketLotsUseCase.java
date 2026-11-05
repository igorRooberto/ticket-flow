package com.igor.Ticket_flow.services;

import com.igor.Ticket_flow.domain.entities.Event;
import com.igor.Ticket_flow.exceptions.NotFoundException;
import com.igor.Ticket_flow.repositories.EventRepository;
import com.igor.Ticket_flow.view.dto.response.EventWithTicketLotsResponseDto;
import com.igor.Ticket_flow.view.dto.response.TicketLotResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetEventWithTicketLotsUseCase {

    private final EventRepository eventRepository;
    public EventWithTicketLotsResponseDto execute(Long eventId){

        Event event = eventRepository.findByWithTicketsLots(eventId)
                .orElseThrow(() -> new NotFoundException("Evento não encontrado"));

        List<TicketLotResponseDto> ticketLotResponseDtos = event.getTicketLots().stream().map(t ->
                new TicketLotResponseDto(t.getId(), t.getName(),t.getPrice(), t.getTotalQuantity(), t.getAvailableQuantity(), t.getSalesStartAt(), t.getSalesEndAt())).toList();

        return new EventWithTicketLotsResponseDto(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getLocation(),
                event.getStartDateTime(),
                event.getEndDateTime(),
                ticketLotResponseDtos
        );
    }
}
