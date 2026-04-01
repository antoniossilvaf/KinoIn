package com.kinoin.model;

import com.kinoin.enums.SeatStatus;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Session {
    private Movie movie;
    private LocalDateTime dateTime;
    private int roomNumber;
    private SeatStatus[][] seatMap;

    public Session(Movie movie, LocalDateTime dateTime, int roomNumber, int rows, int cols) {
        this.movie = movie;
        this.dateTime = dateTime;
        this.roomNumber = roomNumber;
        this.seatMap = new SeatStatus[rows][cols];
        initializeSeats();
    }

    private void initializeSeats() {
        for (SeatStatus[] seatStatuses : seatMap) {
            Arrays.fill(seatStatuses, SeatStatus.AVAIALBLE);
        }
    }

    public boolean isSeatAvaliable(int row, int col) {
        return seatMap[row][col] == SeatStatus.AVAIALBLE;
    }

    public boolean bookSeat(int row, int col) {
        if (isSeatAvaliable(row, col)) {
            seatMap[row][col] = SeatStatus.OCCUPIED;
            return true;
        }
        return false;
    }

    public Movie getMovie() { return movie; }

    public LocalDateTime getDateTime() { return dateTime; }

    public int getRoomNumber() { return roomNumber; }

    public SeatStatus[][] getSeatMap() { return seatMap; }
}
