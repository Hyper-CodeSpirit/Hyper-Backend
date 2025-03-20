package com.hyper.autoshop.service;

import com.hyper.autoshop.dto.invoice.InvoiceDTO;
import com.hyper.autoshop.model.Invoice;
import com.hyper.autoshop.repository.InvoiceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {


    private final InvoiceRepo invoiceRepo;

    public Invoice createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = Invoice.builder()
                .client(invoiceDTO.getClient())
                .vehicle(invoiceDTO.getVehicle())
                .date(invoiceDTO.getDate())
                .dueDate(invoiceDTO.getDueDate())
                .status(invoiceDTO.getStatus())
                .amount(invoiceDTO.getAmount())
                .service(invoiceDTO.getService())
                .build();
        return invoiceRepo.save(invoice);
    }


    public List<Invoice> getAllInvoices() {
        return invoiceRepo.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepo.findById(id).orElse(null);
    }

    public void deleteInvoice(Long id) {
        invoiceRepo.deleteById(id);
    }

    public Invoice updateInvoice(Long id,InvoiceDTO invoiceDTO) {
        Invoice existingInvoice = invoiceRepo.findById(id).orElseThrow(()->new RuntimeException("Invoice not found"));

        existingInvoice.setClient(invoiceDTO.getClient());
        existingInvoice.setVehicle(invoiceDTO.getVehicle());
        existingInvoice.setDate(invoiceDTO.getDate());
        existingInvoice.setDueDate(invoiceDTO.getDueDate());
        existingInvoice.setStatus(invoiceDTO.getStatus());
        existingInvoice.setAmount(invoiceDTO.getAmount());
        existingInvoice.setService(invoiceDTO.getService());
        return invoiceRepo.save(existingInvoice);
    }
}
