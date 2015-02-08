package com.callisto.d5proj.model;

/**
 * Created by emiliano.desantis on 02/09/2014.
 */
public class Trait {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Trait(int id, int value, String description) {
        this.id = id;
        this.value = value;
        this.description = description;
    }

    private int id, value;

    private String description;
}
