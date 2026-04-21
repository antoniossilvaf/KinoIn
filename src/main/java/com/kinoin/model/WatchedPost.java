package com.kinoin.model;

public class WatchedPost extends Post {
    private Movie movie;

    public WatchedPost(User author, Movie movie) {
        super(author);
        this.movie = movie;
    }

    @Override
    public String getPostType() { return "ASSISTIDO"; }

    @Override
    public String getContent() {
        return String.format("Acabou de assistir \"%s\"", movie.getTitle());
    }

    public Movie getMovie() { return movie; }
}
