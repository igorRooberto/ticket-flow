package com.igor.Ticket_flow.exceptions.handler;

import java.time.Instant;

public record ExceptionMessage(
        Instant timeStamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
