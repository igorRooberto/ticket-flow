package com.igor.Ticket_flow.services;

import com.igor.Ticket_flow.domain.entities.Event;
import com.igor.Ticket_flow.exceptions.NotFoundException;
import com.igor.Ticket_flow.repositories.EventRepository;
import com.igor.Ticket_flow.view.dto.response.EventResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetEventUseCase {

    private final EventRepository eventRepository;

    public EventResponseDto execute(Long id){
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento Não Encontrado"));

        return new EventResponseDto(
                event.getId(),
                event.getName(),
                event.getLocation(),
                event.getStartDateTime(),
                event.getEndDateTime());
    }
}
