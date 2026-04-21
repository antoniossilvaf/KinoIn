package com.kinoin.model;

import java.time.LocalDateTime;

public class Session {
    private Movie movie;
    private LocalDateTime dateTime;
    private int roomNumber;
    private double basePrice;
    private Seat[][] seatMap;

    public Session(Movie movie, LocalDateTime dateTime, int roomNumber, double basePrice, boolean[][] vipLayout) {
        this.movie = movie;
        this.dateTime = dateTime;
        this.roomNumber = roomNumber;
        this.basePrice = basePrice;
        this.seatMap = new Seat[vipLayout.length][vipLayout[0].length];
        initializeSeats(vipLayout);
    }

    private void initializeSeats(boolean[][] vipLayout) {
        for (int i = 0; i < seatMap.length; i++)
            for (int j = 0; j < seatMap[i].length; j++)
                seatMap[i][j] = new Seat(i, j, vipLayout[i][j]);
    }

    public boolean isSeatAvaliable(int row, int col) {
        return seatMap[row][col].isAvaliable();
    }

    public boolean selectSeat(int row, int col) {
        Seat seat = seatMap[row][col];
        if (seat.isAvaliable()) {
            seat.select();
            return true;
        }
        return false;
    }

    public boolean bookSeat(int row, int col) {
        Seat seat = seatMap[row][col];
        if (seat.isAvaliable() || seat.isSelected()) {
            seat.occupy();
            return true;
        }
        return false;
    }

    public Movie getMovie() { return movie; }

    public LocalDateTime getDateTime() { return dateTime; }

    public int getRoomNumber() { return roomNumber; }

    public double getBasePrice() { return basePrice; }

    public Seat[][] getSeatMap() { return seatMap; }
}
