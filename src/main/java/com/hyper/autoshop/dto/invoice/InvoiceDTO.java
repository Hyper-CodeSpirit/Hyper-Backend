package com.hyper.autoshop.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InvoiceDTO {
    private String client;
    private String vehicle;
    private LocalDate date;
    private LocalDate dueDate;
    private String status;
    private BigDecimal amount;
    private String service;
}

