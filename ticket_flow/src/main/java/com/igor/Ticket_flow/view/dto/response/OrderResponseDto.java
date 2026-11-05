package com.igor.Ticket_flow.view.dto.response;

import java.math.BigDecimal;

public record OrderResponseDto(

        Long id,
        BigDecimal totalAmount
) {
}
