package com.kinoin.model;

public class ReviewPost extends Post {
    private Review review;

    public ReviewPost(User author, Review review) {
        super(author);
        this.review = review;
    }

    @Override
    public String getPostType() {
        return "REVIEW";
    }

    @Override
    public String getContent() {
        return String.format("Avaliou \"%s\" com %.1f/10\n\"%s\"",
                review.getMovie().getTitle(),
                review.getScore(),
                review.getComment());
    }

    public Review getReview() { return review; }

}
