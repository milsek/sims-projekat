package com.example.library.model;

public abstract class ReservationState {

    protected String stateName;

    private transient Reservation context;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void setContext(Reservation context) {
        this.context = context;
        context.setStateName(stateName);
    }

    public abstract void entry();

    public abstract void doo();
}
