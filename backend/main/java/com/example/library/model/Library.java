package com.example.library.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private String name;

    @OneToMany
    @JoinColumn(name = "library_id")
    private Set<User> users;

    @OneToMany
    @JoinColumn(name = "library_id")
    private Set<Account> accounts;

    @OneToMany
    @JoinColumn(name = "library_id")
    private Set<DailyTransaction> dailyTransactions;

    @OneToMany
    @JoinColumn(name = "library_id")
    private Set<Building> buildings;

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<DailyTransaction> getDailyTransactions() {
        return dailyTransactions;
    }

    public void setDailyTransactions(Set<DailyTransaction> dailyTransactions) {
        this.dailyTransactions = dailyTransactions;
    }

    public Set<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(Set<Building> buildings) {
        this.buildings = buildings;
    }
}
