package com.kinoin.model;

import com.kinoin.enums.SeatStatus;

public class Seat {
    private int row;
    private int column;
    private SeatStatus status;
    private boolean isVipSeat;

    public Seat(int row, int column, boolean isVipSeat) {
        this.row = row;
        this.column = column;
        this.isVipSeat = isVipSeat;
        this.status = SeatStatus.AVAIALBLE;
    }

    public boolean isAvaliable() {
        return this.status == SeatStatus.AVAIALBLE;
    }

    public void occupy() {
        this.status = SeatStatus.OCCUPIED;
    }

    public void release() {
        this.status = SeatStatus.OCCUPIED;
    }

    public int getRow() { return row; }

    public int getColumn() { return column; }

    public SeatStatus getStatus() { return status; }

    public boolean isVipSeat() { return isVipSeat; }
}
