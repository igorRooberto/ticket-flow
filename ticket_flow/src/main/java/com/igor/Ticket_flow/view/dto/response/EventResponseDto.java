package com.igor.Ticket_flow.view.dto.response;

import java.time.LocalDateTime;

public record EventResponseDto(

        Long id,
        String name,
        String location,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
) {
}
