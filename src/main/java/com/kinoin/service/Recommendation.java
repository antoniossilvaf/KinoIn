package com.kinoin.service;

import com.kinoin.model.Movie;
import com.kinoin.model.User;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Recommendation {
    private Catalog catalog;

    public Recommendation(Catalog catalog) {
        this.catalog = catalog;
    }

    public List<Movie> recommend(User user) {
        List<Movie> watched = user.getExpenseHistory().stream()
                .map(t -> t.getSession().getMovie())
                .distinct()
                .toList();
        List<String> preferredGenres = watched.stream()
                .filter(m -> m.getAverageRating() >= 7.0)
                .map(Movie::getGenre)
                .distinct()
                .toList();
        return preferredGenres.stream()
                .flatMap(genre -> catalog.filterByGenre(genre).stream())
                .distinct()
                .filter(m -> !watched.contains(m))
                .sorted(Comparator.comparingDouble(Movie::getAverageRating).reversed())
                .collect(Collectors.toList());
    }
}
