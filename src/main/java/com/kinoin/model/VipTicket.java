package com.kinoin.model;

public class VipTicket extends Ticket {
    private double vipSurcharge;

    public VipTicket(User owner, Session session, int row, int column, double price, double vipSurcharge) {
        super(owner, session, row, column, price);
        this.vipSurcharge = vipSurcharge;
    }

    public double getVipSurcharge() {
        return vipSurcharge;
    }
}
