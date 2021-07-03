package com.example.library.model.dto;

import com.example.library.model.Genre;
import com.example.library.model.Tag;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class EditionDto {

    private Long id;

    private Boolean takeOut;

    private Integer reads;

    private Double rating;

    private String dimensions;

    private Integer pageCount;

    private String description;

    private String language;

    private LocalDate year;

    private String imageSmall;

    private String imageLarge;

    private String titleTitle;

    private String authorName;

    private List<ReviewDisplayDto> reviews;

    private Set<Genre> genres;

    private Set<Tag> tags;

    private String publisherName;

    private int availableCopies;

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getTakeOut() {
        return takeOut;
    }

    public void setTakeOut(Boolean takeOut) {
        this.takeOut = takeOut;
    }

    public Integer getReads() {
        return reads;
    }

    public void setReads(Integer reads) {
        this.reads = reads;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public String getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
    }

    public String getTitleTitle() {
        return titleTitle;
    }

    public void setTitleTitle(String titleTitle) {
        this.titleTitle = titleTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<ReviewDisplayDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDisplayDto> reviews) {
        this.reviews = reviews;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}
