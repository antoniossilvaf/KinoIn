package com.kinoin.model;


import com.kinoin.enums.TicketStatus;

import java.time.Instant;

public class Ticket {
    private static int idCounter = 1000;
    private String ticketId;
    private User owner;
    private int row;
    private int column;
    private double basePrice;
    private Instant purchaseDate;
    private Session session;
    private TicketStatus status;

    public Ticket(User owner, Session session, int row, int column, double basePrice) {
        this.ticketId = "TKT-" + (idCounter++);
        this.owner = owner;
        this.session = session;
        this.row = row;
        this.column = column;
        this.basePrice = basePrice;
        this.purchaseDate = Instant.now();
        this.status = TicketStatus.PAID;
    }

    public void cancel() {
        this.status = TicketStatus.CANCELLED;
    }

    @Override
    public String toString() {
        return String.format("Ticket ID: %s | Movie: %s | Seat: [%d,%d] | Price: %.2f | Status: %s",
                ticketId, session.getMovie().getTitle(), row, column, basePrice, status);
    }

    public String getTicketId() { return ticketId; }

    public User getOwner() { return owner; }

    public int getRow() { return row; }

    public int getColumn() { return column; }

    public double getBasePrice() {return basePrice; }

    public Instant getPurchaseDate() { return purchaseDate; }

    public Session getSession() { return session; }

    public TicketStatus getStatus() { return status; }
}
