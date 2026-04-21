package com.kinoin.model;

public class ConcessionTicket extends Ticket{
    private String concessionType;

    public ConcessionTicket(User owner, Session session, int row, int column, String concessionType) {
        super(owner, session, row, column, session.getBasePrice() / 2);
        this.concessionType = concessionType;
    }

    public String getConcessionType() { return concessionType; }
}
