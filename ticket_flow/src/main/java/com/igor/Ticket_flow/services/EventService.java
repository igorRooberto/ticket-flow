package com.igor.Ticket_flow.services;

import com.igor.Ticket_flow.domain.entities.Event;
import com.igor.Ticket_flow.domain.entities.TicketLot;
import com.igor.Ticket_flow.domain.enums.EventStatus;
import com.igor.Ticket_flow.exceptions.BadRequestException;
import com.igor.Ticket_flow.exceptions.NotFoundException;
import com.igor.Ticket_flow.repositories.EventRepository;
import com.igor.Ticket_flow.view.dto.request.CreateEventRequestDto;
import com.igor.Ticket_flow.view.dto.response.EventResponseDto;
import com.igor.Ticket_flow.view.dto.response.EventWithTicketLotsResponseDto;
import com.igor.Ticket_flow.view.dto.response.TicketLotResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public EventResponseDto createEvent(CreateEventRequestDto dto){

        if(dto.startDateTime().isBefore(LocalDateTime.now())){
            throw new BadRequestException("Hora De inicio inválida");
        }

        if(dto.startDateTime().isAfter(dto.endDateTime())){
            throw new BadRequestException("Hora De inicio inválida");
        }

        Event event = Event.builder()
                .name(dto.name())
                .description(dto.description())
                .location(dto.location())
                .startDateTime(dto.startDateTime())
                .endDateTime(dto.endDateTime())
                .status(EventStatus.PUBLISHED)
                .build();

        Event eventSaved = eventRepository.save(event);

        return new EventResponseDto(eventSaved.getId(),
                eventSaved.getName(),
                eventSaved.getLocation(),
                eventSaved.getStartDateTime(),
                eventSaved.getEndDateTime());
    }

    public EventResponseDto getEventById(Long id){
        Event event = findEventById(id);

        return new EventResponseDto(
                event.getId(),
                event.getName(),
                event.getLocation(),
                event.getStartDateTime(),
                event.getEndDateTime());
    }

    public EventWithTicketLotsResponseDto getEventWithTicketsLots(Long eventId){

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
    public Event findEventById(Long id){
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento Não Encontrado"));
    }

}
