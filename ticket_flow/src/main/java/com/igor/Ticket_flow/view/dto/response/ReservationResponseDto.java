package com.igor.Ticket_flow.view.dto.response;

import java.time.Instant;

public record ReservationResponseDto(

        Long id,
        Integer quantity,
        Instant expiresAt
) {
}
