package com.igor.Ticket_flow.domain.entities;

import com.igor.Ticket_flow.domain.enums.EventStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_event")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id",nullable = false,unique = true,updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description",nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "location",nullable = false)
    private String location;

    @Column(name = "start_date_time",nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time",nullable = false)
    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EventStatus status;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private Instant createdAt;

    protected Event() {
    }

    @Builder
    public Event(String name, String description, String location, LocalDateTime startDateTime, LocalDateTime endDateTime, EventStatus status) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.status = status;
    }
}
