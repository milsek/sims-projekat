package com.example.library.model.dto;

import java.time.LocalDate;

public class UserLoginDto {

    private Long id;

    private String name;

    private String surname;

    private LocalDate birthdate;

    private Integer discriminatorValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getDiscriminatorValue() {
        return discriminatorValue;
    }

    public void setDiscriminatorValue(Integer discriminatorValue) {
        this.discriminatorValue = discriminatorValue;
    }
}
