package com.igor.Ticket_flow.exceptions.handler;

import com.igor.Ticket_flow.exceptions.BadRequestException;
import com.igor.Ticket_flow.exceptions.ConflictException;
import com.igor.Ticket_flow.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleNotFoundException(NotFoundException ex, HttpServletRequest request){
        ExceptionMessage message = new ExceptionMessage(
                Instant.now(),
                404,
                "NOT_FOUND",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionMessage> handleBadRequestException(BadRequestException ex, HttpServletRequest request){
        ExceptionMessage message = new ExceptionMessage(
                Instant.now(),
                400,
                "BAD_REQUEST",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ExceptionMessage> handleConflictException(ConflictException ex, HttpServletRequest request){
        ExceptionMessage message = new ExceptionMessage(
                Instant.now(),
                409,
                "CONFLICT",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
        ExceptionMessage message = new ExceptionMessage(
                Instant.now(),
                400,
                "BAD_REQUEST",
                ex.getBindingResult().getFieldError().getDefaultMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionMessage> handleMissingParam(MissingServletRequestParameterException ex, HttpServletRequest request){

        var defaultMessage = "O parâmetro '" + ex.getParameterName() + "' é obrigatório";

        ExceptionMessage message = new ExceptionMessage(
                Instant.now(),
                400,
                "BAD_REQUEST",
                defaultMessage,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
