package com.hyper.autoshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Builder
@Table(name = "invoices", schema = "hyper")
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name="client_name", nullable = false, length = 200)
    private String client;

    private String vehicle;

    @Column(name="invoice_date", nullable = false)
    private LocalDate date;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(length = 20)
    private String status;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "service_description", nullable = false, columnDefinition = "TEXT")
    private String service;

}
