package com.igor.Ticket_flow.view.controller;

import com.igor.Ticket_flow.services.CreateEventUseCase;
import com.igor.Ticket_flow.services.GetEventUseCase;
import com.igor.Ticket_flow.services.GetEventWithTicketLotsUseCase;
import com.igor.Ticket_flow.view.dto.request.CreateEventRequestDto;
import com.igor.Ticket_flow.view.dto.response.EventResponseDto;
import com.igor.Ticket_flow.view.dto.response.EventWithTicketLotsResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

    private final CreateEventUseCase createEventUseCase;
    private final GetEventUseCase getEventUseCase;
    private final GetEventWithTicketLotsUseCase getEventWithTicketLotsUseCase;

    @PostMapping("/create")
    public ResponseEntity<EventResponseDto> createEvent(@RequestBody @Valid CreateEventRequestDto dto){
        EventResponseDto responseDto = createEventUseCase.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping()
    public ResponseEntity<EventResponseDto> getEvent(
            @RequestParam("eventId") Long id) {
        return ResponseEntity.ok(getEventUseCase.execute(id));
    }

    @GetMapping("/ticket")
    public ResponseEntity<EventWithTicketLotsResponseDto> getEventWithTicketsLots(
            @RequestParam("eventId") Long id) {
        return ResponseEntity.ok(getEventWithTicketLotsUseCase.execute(id));
    }

}
