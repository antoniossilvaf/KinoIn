package com.kinoin.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private List<Movie> wishlist;
    private List<User> friends;
    private List<Ticket> expenseHistory;

    public User (String name, String email) {
        this.name = name;
        this.email = email;

        this.wishlist = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.expenseHistory = new ArrayList<>();
    }

    public void addToWishlist(Movie movie) {
        if (!wishlist.contains(movie)) {
            wishlist.add(movie);
        }
    }

    public void addFriend(User friend) {
        if (!friends.contains(friend) && friend != this) {
            friends.add(friend);
        }
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) { this.email = email; }

}
