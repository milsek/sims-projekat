package com.example.library.model;

public abstract class BookState {
    protected String stateName;

    private transient Book context;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void setContext(Book context) {
        this.context = context;
        context.setStateName(stateName);
    }

    public abstract void entry();

    public abstract void doo();
}
