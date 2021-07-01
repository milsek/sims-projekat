package com.example.library.model.dto;

public class SelectedBookDto {

    private Long id;

    private String editionImageLarge;

    private String editionTitleTitle;

    private String authorName;

    private String lineIsleName;

    private Integer lineNumber;

    private String bookState;

    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SelectedBookDto(String editionImageLarge, String editionTitleTitle, String authorName, String lineIsleName, Integer lineNumber, String bookState) {
        this.editionImageLarge = editionImageLarge;
        this.editionTitleTitle = editionTitleTitle;
        this.authorName = authorName;
        this.lineIsleName = lineIsleName;
        this.lineNumber = lineNumber;
        this.bookState = bookState;
    }

    public SelectedBookDto() {
    }

    public String getEditionImageLarge() {
        return editionImageLarge;
    }

    public void setEditionImageLarge(String editionImageLarge) {
        this.editionImageLarge = editionImageLarge;
    }

    public String getEditionTitleTitle() {
        return editionTitleTitle;
    }

    public void setEditionTitleTitle(String editionTitleTitle) {
        this.editionTitleTitle = editionTitleTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getLineIsleName() {
        return lineIsleName;
    }

    public void setLineIsleName(String lineIsleName) {
        this.lineIsleName = lineIsleName;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getBookState() {
        return bookState;
    }

    public void setBookState(String bookState) {
        this.bookState = bookState;
    }
}