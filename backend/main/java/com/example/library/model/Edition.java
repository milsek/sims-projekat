package com.example.library.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Edition extends BookType {

    @Column
    private boolean takeOut;

    @Column
    private int reads;

    @Column
    private double rating;

    @Column
    private String dimensions;

    @Column
    private String language;

    @Column
    private LocalDate year;

    @ManyToMany
    @JoinTable(
            name = "edition_content",
            joinColumns = @JoinColumn(name = "edition_id"),
            inverseJoinColumns = @JoinColumn(name = "content_id")
    )
    private Set<Content> contents;

    @ManyToMany
    @JoinTable(
            name = "edition_tags",
            joinColumns = @JoinColumn(name = "edition_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;


    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "edition_contributors",
            joinColumns = @JoinColumn(name = "edition_id"),
            inverseJoinColumns = @JoinColumn(name = "contributor_id")
    )
    private Set<Contributor> contributors;

    @ManyToOne
    private Author author;

}
