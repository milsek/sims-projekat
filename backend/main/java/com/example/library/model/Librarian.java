package com.example.library.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Librarian extends User {

    @Column
    private Workplace workplace;

    @Override
    public Integer getDiscriminatorValue() {
        DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );
        return val == null ? null : Integer.valueOf(val.value());
    }
}
