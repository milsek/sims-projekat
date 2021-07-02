package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("0")
@Indexed
public class Member extends User {

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Reservation> reservations;

    @OneToMany
    @JoinColumn(name = "user_id")
    @JsonIgnore
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


    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }


    @Override
    public Integer getDiscriminatorValue() {
        DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );
        return val == null ? null : Integer.valueOf(val.value());
    }
}
