package com.example.library.model.dto;

public class EditionDisplayDto {

    private Long id;

    private String titleTitle;

    private Integer availableCopies;

    private Float rating;

    private Integer reads;

    private String imageLarge;

    private String description;

    private String authorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleTitle() {
        return titleTitle;
    }

    public void setTitleTitle(String titleTitle) {
        this.titleTitle = titleTitle;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getReads() {
        return reads;
    }

    public void setReads(Integer reads) {
        this.reads = reads;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}

/*
data.id
data.title.title
data.availableCopies
data.rating
data.reads
data.imageLarge
*/