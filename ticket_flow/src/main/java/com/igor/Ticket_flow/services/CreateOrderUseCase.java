package com.igor.Ticket_flow.services;
import com.igor.Ticket_flow.domain.entities.Customer;
import com.igor.Ticket_flow.domain.entities.Order;
import com.igor.Ticket_flow.domain.entities.Reservation;
import com.igor.Ticket_flow.domain.entities.TicketLot;
import com.igor.Ticket_flow.domain.enums.OrderStatus;
import com.igor.Ticket_flow.domain.enums.ReservationStatus;
import com.igor.Ticket_flow.exceptions.ConflictException;
import com.igor.Ticket_flow.exceptions.NotFoundException;
import com.igor.Ticket_flow.repositories.OrderRepository;
import com.igor.Ticket_flow.repositories.ReservationRepository;
import com.igor.Ticket_flow.view.dto.response.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final ReservationRepository reservationRepository;

    public OrderResponseDto execute(Long reservationId){

        Reservation reservation = reservationRepository.findByReservationWithTicketLotAndCustomer(reservationId)
                .orElseThrow(() -> new NotFoundException("Reserva Não Encontrada"));

        reservation.checkReservation();

        TicketLot ticketLot = reservation.getTicketLot();
        Customer customer = reservation.getCustomer();

        Integer quantityTicket = reservation.getQuantity();
        BigDecimal totalAmount = ticketLot.getPrice()
                .multiply(BigDecimal.valueOf(quantityTicket));

        Order order = Order.builder()
                .totalAmount(totalAmount)
                .status(OrderStatus.PENDING)
                .reservation(reservation)
                .customer(customer)
                .build();

        Order orderSaved = orderRepository.save(order);
        return new OrderResponseDto(orderSaved.getId(), orderSaved.getTotalAmount());
    }

}
