package com.igor.Ticket_flow.view.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateTicketLotRequestDto(

        @NotBlank(message = "O nome do lote é obrigatório")
        String name,

        @NotNull(message = "O preço do lote é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
        BigDecimal price,

        @NotNull(message = "A quantidade total é obrigatória")
        @Positive(message = "A quantidade total deve ser maior que zero")
        Integer totalQuantity,

        @NotNull(message = "A data de início das vendas é obrigatória")
        LocalDateTime salesStartAt,

        @NotNull(message = "A data de encerramento das vendas é obrigatória")
        LocalDateTime salesEndAt,

        @NotNull(message = "O evento associado ao lote é obrigatório")
        Long eventId
) {
}
