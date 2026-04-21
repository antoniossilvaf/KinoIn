package com.kinoin.model;

public class Alert {
    private User user;
    private Movie movie;
    private boolean triggered;

    public Alert(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
        this.triggered = false;
    }

    public void trigger() {
        this.triggered = true;
    }

    public boolean isTriggered() { return triggered; }

    public User getUser() { return user; }

    public Movie getMovie() { return movie; }
}
