package com.kinoin.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Transaction {
    private String id;
    private User customer;
    private List<Ticket> tickets;
    private double totalAmount;
    private String paymentMethod;
    private LocalDateTime timestamp;

    public Transaction(User customer, List<Ticket> tickets, String paymentMethod) {
        this.id = "TRX-" + UUID.randomUUID().toString().substring(0, 8);
        this.customer = customer;
        this.tickets = tickets;
        this.paymentMethod = paymentMethod;
        this.timestamp = LocalDateTime.now();
        this.totalAmount = calculateTotal();
    }

    public double calculateTotal() {
        return tickets.stream().mapToDouble(Ticket::getBasePrice).sum();
    }

    public String getId() { return id; }

    public User getCustomer() { return customer; }

    public List<Ticket> getTickets() { return tickets; }

    public double getTotalAmount() { return totalAmount; }

    public String getPaymentMethod() { return paymentMethod; }

    public LocalDateTime getTimestamp() { return timestamp; }
}
