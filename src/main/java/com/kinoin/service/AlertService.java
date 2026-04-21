package com.kinoin.service;

import com.kinoin.enums.MovieStatus;
import com.kinoin.model.Alert;
import com.kinoin.model.Movie;
import com.kinoin.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlertService {
    private List<Alert> alerts = new ArrayList<>();

    // Só alerta se o filme estiver na wishlist do usuário
    public Alert createAlert(User user, Movie movie) {
        if (!user.getWishlist().contains(movie)) {
            throw new IllegalArgumentException(
                    "\"" + movie.getTitle() + "\" não está na wishlist de " + user.getName()
            );
        }
        Alert alert = new Alert(user, movie);
        alerts.add(alert);
        return alert;
    }

    // quando filme mudar de status
    public void checkAlerts(Movie movie) {
        if (movie.getMovieStatus() == MovieStatus.NOW_SHOWING) {
            alerts.stream()
                    .filter(a -> a.getMovie().equals(movie))
                    .filter(a -> !a.isTriggered())
                    .filter(a -> a.getUser().getWishlist().contains(movie))
                    .forEach(Alert::trigger);
        }
    }

    public List<Alert> getTriggeredAlertsForUser(User user) {
        return alerts.stream()
                .filter(a -> a.getUser().equals(user) && a.isTriggered())
                .collect(Collectors.toList());
    }
}
