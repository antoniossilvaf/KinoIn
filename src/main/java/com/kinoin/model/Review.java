package com.kinoin.model;

import java.time.LocalDate;

public class Review {
    private User user;
    private Movie movie;
    private double score;
    private String comment;
    private LocalDate date;

    public Review(User user, Movie movie, double score, String comment) {
        this.user = user;
        this.movie = movie;
        this.score = score;
        this.comment = comment;
        this.date = LocalDate.now();

        movie.addReview(this);
    }

    public User getUser() {return user;}

    public void setUser(User author) {
        this.user = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDate getDate() {
        return date;
    }
}