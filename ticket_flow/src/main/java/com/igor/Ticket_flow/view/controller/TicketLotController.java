package com.igor.Ticket_flow.view.controller;

import com.igor.Ticket_flow.services.TicketLotService;
import com.igor.Ticket_flow.view.dto.request.CreateTicketLotRequestDto;
import com.igor.Ticket_flow.view.dto.response.TicketLotResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketLotController {

    private final TicketLotService ticketLotService;

    @PostMapping("/create/lot")
    public ResponseEntity<TicketLotResponseDto> createTicketLot(@RequestBody @Valid CreateTicketLotRequestDto dto){
        TicketLotResponseDto responseDto = ticketLotService.createTicketLot(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
