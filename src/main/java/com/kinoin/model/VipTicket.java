package com.kinoin.model;

public class VipTicket extends Ticket {
    private double vipSurcharge;

    public VipTicket(User owner, Session session, int row, int column, double vipSurcharge) {
        super(owner, session, row, column, session.getBasePrice() + vipSurcharge);
        this.vipSurcharge = vipSurcharge;
    }

    public double getVipSurcharge() {
        return vipSurcharge;
    }
}
