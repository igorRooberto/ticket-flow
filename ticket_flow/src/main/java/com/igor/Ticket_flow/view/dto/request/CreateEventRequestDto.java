package com.igor.Ticket_flow.view.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateEventRequestDto(

        @NotBlank(message = "O nome do evento é obrigatório")
        String name,

        @NotBlank(message = "A descrição do evento é obrigatória")
        String description,

        @NotBlank(message = "O local do evento é obrigatório")
        String location,

        @NotNull(message = "A data e hora de início do evento são obrigatórias")
        LocalDateTime startDateTime,

        @NotNull(message = "A data e hora de término do evento são obrigatórias")
        LocalDateTime endDateTime

) {
}
