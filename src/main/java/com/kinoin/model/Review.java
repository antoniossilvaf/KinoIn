package com.kinoin.model;

import java.time.LocalDate;

public class Review {
    private User author;
    private Movie movie;
    private double score;
    private String comment;
    private LocalDate date;

    public Review(User author, Movie movie, double score, String comment) {
        this.author = author;
        this.movie = movie;
        this.score = score;
        this.comment = comment;
        this.date = LocalDate.now();

        movie.addReview(this);
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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