package com.kinoin.model;

import com.kinoin.enums.FriendshipStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private String name;
    private String email;
    private List<Movie> wishlist;
    private List<Friendship> friendships;
    private List<Ticket> expenseHistory;

    public User (String name, String email) {
        this.name = name;
        this.email = email;

        this.wishlist = new ArrayList<>();
        this.friendships = new ArrayList<>();
        this.expenseHistory = new ArrayList<>();
    }

    public void addToWishlist(Movie movie) {
        if (!wishlist.contains(movie)) {
            wishlist.add(movie);
        }
    }

    public void removeFromWishlist(Movie movie) {
        wishlist.remove(movie);
    }

    public Friendship addFriend(User receiver) {
        Friendship friendship = new Friendship(this, receiver);
        this.friendships.add(friendship);
        receiver.friendships.add(friendship);
        return friendship;
    }

    public void addTicketToHistory(Ticket ticket) {
        expenseHistory.add(ticket);
    }

    public List<User> getFriends() {
        return friendships.stream()
                .filter(f -> f.getStatus() == FriendshipStatus.ACCEPTED)
                .map(f -> f.getOther(this))
                .collect(Collectors.toList());
    }

    public List<Friendship> getFriendships() { return friendships; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) { this.email = email; }

    public List<Movie> getWishlist() { return wishlist; }

    public List<Ticket> getExpenseHistory() { return expenseHistory; }
}
