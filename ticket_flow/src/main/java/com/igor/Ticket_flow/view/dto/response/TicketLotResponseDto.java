package com.igor.Ticket_flow.view.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketLotResponseDto(

        Long id,
        String name,
        BigDecimal price,
        Integer totalQuantity,
        Integer availableQuantity,
        LocalDateTime salesStartAt,
        LocalDateTime salesEndAt
) {
}
