package com.kinoin.model;

import com.kinoin.enums.MovieStatus;

import java.util.List;

public class Movie {
    private String title;
    private String genre;
    private int ratingAge;
    private MovieStatus status;
    private double averageRating;
    private int totalRatings;
    private List<Review> reviews;

    public Movie(String title, String genre, int ratingAge, MovieStatus status) {
        this.title = title;
        this.genre = genre;
        this.ratingAge = ratingAge;
        this.status = status;
        this.averageRating = 0.0;
        this.totalRatings = 0;
    }

    public void addReview(Review review) {
        double newRating = review.getScore();

        double currentTotalPoints = this.averageRating * totalRatings;
        this.totalRatings++;
        this.averageRating = (currentTotalPoints + newRating) / this.totalRatings;

        this.reviews.add(review);
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getRatingAge() {
        return ratingAge;
    }

    public MovieStatus getMovieStatus() {
        return status;
    }

    public void setMovieStatus(MovieStatus status) {
        this.status = status;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getTotalRatings() {
        return totalRatings;
    }
}
