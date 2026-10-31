package com.igor.Ticket_flow.domain.entities;

import com.igor.Ticket_flow.domain.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_reservation")
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,updatable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private ReservationStatus status;

    @Column(name = "expired_at", nullable = false)
    private Instant expiresAt;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ticket_lot_id",nullable = false)
    private TicketLot ticketLot;

    protected Reservation(){

    }

    @Builder
    public Reservation(Integer quantity, ReservationStatus status, Instant expiresAt, Customer customer, TicketLot ticketLot) {
        this.quantity = quantity;
        this.status = status;
        this.expiresAt = expiresAt;
        this.customer = customer;
        this.ticketLot = ticketLot;
    }
}
