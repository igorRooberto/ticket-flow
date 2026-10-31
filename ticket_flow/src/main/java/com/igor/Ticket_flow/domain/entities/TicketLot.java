package com.igor.Ticket_flow.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_ticket_lot")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public class TicketLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "total_quantity",nullable = false)
    private Integer totalQuantity;

    @Column(name = "available_quantity",nullable = false)
    private Integer availableQuantity;

    @Column(name = "sales_start_at",nullable = false)
    private LocalDateTime salesStartAt;

    @Column(name = "sales_end_at",nullable = false)
    private LocalDateTime salesEndAt;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id",nullable = false)
    private Event event;

    protected TicketLot(){

    }

    @Builder
    public TicketLot(String name, BigDecimal price, Integer totalQuantity, Integer availableQuantity, LocalDateTime salesStartAt, LocalDateTime salesEndAt, Event event) {
        this.name = name;
        this.price = price;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = availableQuantity;
        this.salesStartAt = salesStartAt;
        this.salesEndAt = salesEndAt;
        this.event = event;
    }
}
