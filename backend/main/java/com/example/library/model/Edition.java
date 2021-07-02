package com.example.library.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "@id")
@Indexed
public class Edition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private boolean takeOut;

    @Column
    private int reads;

    @Column
    private double rating;

    @Column
    private String dimensions;

    @Column
    private int pageCount;

    @Column(length = 1000)
    @Field
    private String description;

    @Column
    private String language;

    @Column
    private LocalDate year;

    @Column
    private String imageSmall;

    @Column
    private String imageLarge;

    @ManyToOne
    @IndexedEmbedded(depth = 4)
    private BookTitle title;

    @Column
    private int availableCopies;

    @ManyToMany
    @JoinTable(
            name = "edition_content",
            joinColumns = @JoinColumn(name = "edition_id"),
            inverseJoinColumns = @JoinColumn(name = "content_id")
    )
    @IndexedEmbedded(depth = 1)
    private Set<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "edition_tags",
            joinColumns = @JoinColumn(name = "edition_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @IndexedEmbedded(depth = 1)
    private Set<Tag> tags;


    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    @IndexedEmbedded(depth = 1)
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "edition_contributions",
            joinColumns = @JoinColumn(name = "edition_id"),
            inverseJoinColumns = @JoinColumn(name = "contribution_id")
    )
    @IndexedEmbedded(depth = 3)
    private List<Contribution> contributions;

    @OneToMany(mappedBy = "edition")
    private Set<Book> copies;


//    @ManyToOne
//    @IndexedEmbedded(depth = 1)
//    private Author author;

    public boolean isTakeOut() {
        return takeOut;
    }

    public void setTakeOut(boolean takeOut) {
        this.takeOut = takeOut;
    }

    public int getReads() {
        return reads;
    }

    public void setReads(int reads) {
        this.reads = reads;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
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

    public Set<Genre> getContents() {
        return genres;
    }

    public void setContents(Set<Genre> contents) {
        this.genres = contents;
    }

    @JsonIgnore
    public Set<Tag> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public Set<Long> getTagsId() {
        Set<Long> set = new HashSet<>();
        tags.stream().map(Tag::getId).forEachOrdered(set::add);
        return set;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Contribution> getContributions() {
        return contributions;
    }

    public void setContributions(List<Contribution> contributions) {
        this.contributions = contributions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BookTitle getTitle() {
        return title;
    }

    public void setTitle(BookTitle title) {
        this.title = title;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}
