package com.example.library.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("1")
public class Member extends User {

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Reservation> reservations;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Membership> memberships;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }

    @Override
    public Integer getDiscriminatorValue() {
        DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );
        return val == null ? null : Integer.valueOf(val.value());
    }
}
