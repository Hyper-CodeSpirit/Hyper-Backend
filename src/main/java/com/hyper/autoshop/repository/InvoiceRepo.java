package com.hyper.autoshop.repository;

import com.hyper.autoshop.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepo  extends JpaRepository<Invoice, Long> {
}
