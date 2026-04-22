package com.kinoin.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Feed {

    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        posts.add(post);
    }

    public List<Post> getAllPosts() {
        return posts.stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    // 🔥 ATUALIZADO: inclui amigos + o próprio usuário
    public List<Post> getFriendFeed(User user) {
        return posts.stream()
                .filter(p ->
                        user.getFriends().contains(p.getAuthor())
                                || p.getAuthor().equals(user) // 👈 ESSENCIAL
                )
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
}