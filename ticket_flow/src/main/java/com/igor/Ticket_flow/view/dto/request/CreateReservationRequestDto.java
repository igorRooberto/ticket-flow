package com.igor.Ticket_flow.view.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateReservationRequestDto(

        @NotNull(message = "A quantidade é obrigatória")
        @Positive(message = "A quantidade deve ser maior que zero")
        Integer quantity,

        @NotNull(message = "O ID do cliente é obrigatório")
        Long customerId,

        @NotNull(message = "O ID do lote de ingressos é obrigatório")
        Long ticketLotId
) {
}
