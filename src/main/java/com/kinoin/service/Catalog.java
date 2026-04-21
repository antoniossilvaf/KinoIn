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

    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }

    // Todos os filmes
    public List<Movie> getAllMovies() {
        return cinemas.stream()
                .flatMap(c -> c.getSessions().stream())
                .map(Session::getMovie)
                .distinct()
                .collect(Collectors.toList());

    }

    // Saber cinemas em que o filme está passando
    public List<Cinema> getCinemasForMovie (Movie movie) {
        return cinemas.stream()
                .filter(cinema -> cinema.getSessions().stream()
                        .anyMatch(session -> session.getMovie().equals(movie)))
                .collect(Collectors.toList());
    }

    // filtros

    public List<Movie> filterByGenre(String genre) {
        return getAllMovies().stream()
                .filter(m -> m.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    public List<Movie> filterByStatus(MovieStatus status) {
        return getAllMovies().stream()
                .filter(m -> m.getMovieStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Movie> filterByMaxAge(int maxAge) {
        return getAllMovies().stream()
                .filter(m -> m.getRatingAge() <= maxAge)
                .collect(Collectors.toList());
    }

    // Ordenação por avaliação

    public List<Movie> sortByRatingDesc() {
        return getAllMovies().stream()
                .sorted(Comparator.comparingDouble(Movie::getAverageRating).reversed())
                .collect(Collectors.toList());
    }

    public List<Movie> sortByRatingAsc() {
        return getAllMovies().stream()
                .sorted(Comparator.comparingDouble(Movie::getAverageRating))
                .collect(Collectors.toList());
    }

    // Filtrado por genero e ordenado por avaliação

    public List<Movie> filterByGenreSortedByRating(String genre) {
        return filterByGenre(genre).stream()
                .sorted(Comparator.comparingDouble(Movie::getAverageRating).reversed())
                .collect(Collectors.toList());
    }

    public List<Cinema> getCinemas() { return cinemas; }

}
