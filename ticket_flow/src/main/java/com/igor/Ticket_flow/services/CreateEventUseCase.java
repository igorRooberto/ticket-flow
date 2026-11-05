package com.igor.Ticket_flow.services;

import com.igor.Ticket_flow.domain.entities.Event;
import com.igor.Ticket_flow.domain.enums.EventStatus;
import com.igor.Ticket_flow.exceptions.BadRequestException;
import com.igor.Ticket_flow.repositories.EventRepository;
import com.igor.Ticket_flow.view.dto.request.CreateEventRequestDto;
import com.igor.Ticket_flow.view.dto.response.EventResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateEventUseCase {

    private final EventRepository eventRepository;

    public EventResponseDto execute(CreateEventRequestDto dto){

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
}
