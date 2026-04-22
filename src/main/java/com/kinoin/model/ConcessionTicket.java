package com.kinoin.model;

public class ConcessionTicket extends Ticket {

    public ConcessionTicket(User user, Session session, int row, int col) {
        super(user, session, row, col, session.getBasePrice() * 0.5);
    }

    @Override
    public String toString() {
        return "CONCESSION - " + super.toString();
    }
}