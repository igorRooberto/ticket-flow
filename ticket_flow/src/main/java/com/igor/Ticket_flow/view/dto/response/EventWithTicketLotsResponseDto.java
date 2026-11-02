package com.igor.Ticket_flow.view.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record EventWithTicketLotsResponseDto(

        Long id,
        String name,
        String description,
        String location,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        List<TicketLotResponseDto> listTicketLot
) {
}
