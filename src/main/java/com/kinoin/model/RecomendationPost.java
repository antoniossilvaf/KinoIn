package com.kinoin.model;

public class RecomendationPost extends Post {
    private Movie movie;
    private String reason;

    public RecomendationPost(User author, Movie movie, String reason) {
        super(author);
        this.movie = movie;
        this.reason = reason;
    }

    @Override
    public String getPostType() { return "RECOMENDAÇÃO"; }

    @Override
    public String getContent() {
        return String.format("Recomenda \"%s\": %s", movie.getTitle(), reason);
    }

    public Movie getMovie() { return movie; }
    public String getReason() { return reason; }
}
