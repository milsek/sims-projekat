package com.example.library.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.bridge.builtin.LongBridge;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Field(name = "member_id")
    @FieldBridge(impl = LongBridge.class)
    protected long id;

    @Column
    @Field(name = "member_name")
    protected String name;

    @Column
    @Field(name = "member_surname")
    protected String surname;

    @Column
    protected LocalDate birthdate;

    @Transient
    public abstract Integer getDiscriminatorValue();

    @OneToOne(targetEntity = Account.class)
    protected Account account;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
