package com.kinoin.service;

import com.kinoin.enums.MovieStatus;
import com.kinoin.model.Cinema;
import com.kinoin.model.Movie;
import com.kinoin.model.Session;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Catalog {

    private List<Cinema> cinemas = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();

    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }

    public void addMovie(Movie movie) {
        if (!movies.contains(movie)) {
            movies.add(movie);
        }
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies);
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public List<Cinema> getCinemasForMovie(Movie movie) {
        return cinemas.stream()
                .filter(cinema -> cinema.getSessions().stream()
                        .anyMatch(session -> session.getMovie().equals(movie)))
                .collect(Collectors.toList());
    }


    public List<Movie> filterByGenre(String genre) {
        return movies.stream()
                .filter(m -> m.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    public List<Movie> filterByStatus(MovieStatus status) {
        return movies.stream()
                .filter(m -> m.getMovieStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Movie> filterByMaxAge(int maxAge) {
        return movies.stream()
                .filter(m -> m.getRatingAge() <= maxAge)
                .collect(Collectors.toList());
    }

    public List<Movie> sortByRatingDesc() {
        return movies.stream()
                .sorted(Comparator.comparingDouble(Movie::getAverageRating).reversed())
                .collect(Collectors.toList());
    }

    public List<Movie> sortByRatingAsc() {
        return movies.stream()
                .sorted(Comparator.comparingDouble(Movie::getAverageRating))
                .collect(Collectors.toList());
    }

    public List<Movie> filterByGenreSortedByRating(String genre) {
        return movies.stream()
                .filter(m -> m.getGenre().equalsIgnoreCase(genre))
                .sorted(Comparator.comparingDouble(Movie::getAverageRating).reversed())
                .collect(Collectors.toList());
    }
}