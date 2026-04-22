package com.kinoin.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Post {
    private String id;
    private User author;
    private LocalDateTime createdAt;
    private List<User> likes;

    public Post(User author) {
        this.id = "POST-" + UUID.randomUUID().toString().substring(0, 8);
        this.author = author;
        this.createdAt = LocalDateTime.now();
        this.likes = new ArrayList<>();
    }

    public abstract String getContent();
    public abstract String getPostType();

    public boolean toggleLike(User user) {
        if (likes.contains(user)) {
            likes.remove(user);
            return false;
        } else {
            likes.add(user);
            return true;
        }
    }

    public boolean isLikedBy(User user) {
        return likes.contains(user);
    }

    public int getLikesCount() { return likes.size(); }

    public String getId() { return id; }

    public List<User> getLikes() { return likes; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public User getAuthor() { return author; }

}
