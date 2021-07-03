package com.example.library.model.dto;

public class ReviewDisplayDto {

    private Long id;

    private String content;

    private double rating;

    private String fullName;

    private String editionTitleTitle;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEditionTitleTitle() {
        return editionTitleTitle;
    }

    public void setEditionTitleTitle(String editionTitleTitle) {
        this.editionTitleTitle = editionTitleTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
