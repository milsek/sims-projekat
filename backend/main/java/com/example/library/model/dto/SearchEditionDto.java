package com.example.library.model.dto;

import java.util.List;

public class SearchEditionDto {

    private Long hitCount;

    private List<EditionDisplayDto> displayDtoList;

    public Long getHitCount() {
        return hitCount;
    }

    public void setHitCount(Long hitCount) {
        this.hitCount = hitCount;
    }

    public List<EditionDisplayDto> getDisplayDtoList() {
        return displayDtoList;
    }

    public void setDisplayDtoList(List<EditionDisplayDto> displayDtoList) {
        this.displayDtoList = displayDtoList;
    }
}
