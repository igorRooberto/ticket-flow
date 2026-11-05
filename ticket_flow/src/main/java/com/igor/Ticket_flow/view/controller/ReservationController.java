package com.igor.Ticket_flow.view.controller;

import com.igor.Ticket_flow.services.CreateReservationUseCase;
import com.igor.Ticket_flow.view.dto.request.CreateReservationRequestDto;
import com.igor.Ticket_flow.view.dto.response.ReservationResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final CreateReservationUseCase createReservationUseCase;

    @PostMapping()
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestBody @Valid CreateReservationRequestDto dto){
        ReservationResponseDto responseDto = createReservationUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
