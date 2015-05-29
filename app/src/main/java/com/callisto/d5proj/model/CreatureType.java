package com.callisto.d5proj.model;

import java.util.List;

/**
 * Created by emiliano.desantis on 02/09/2014.
 */
public class CreatureType {
    protected enum Rating {
        BAD, AVERAGE, GOOD
    }

    public CreatureType() {
    }

    public CreatureType(int id, int dieSize, int skills, Rating atkClass, List<Trait> traits, String name) {
        this.id = id;
        this.dieSize = dieSize;
        this.skills = skills;
        this.atkRating = atkClass;
        this.traits = traits;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Trait> getTraits() {
        return traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }

    public int getDieSize() {
        return dieSize;
    }

    public void setDieSize(int dieSize) {
        this.dieSize = dieSize;
    }

    public int getSkills() {
        return skills;
    }

    public void setSkills(int skills) {
        this.skills = skills;
    }

    public Rating getAtkRating() {
        return atkRating;
    }

    public void setAtkRating(Rating atkRating) {
        this.atkRating = atkRating;
    }

    // Fields
    private int id, dieSize, skills;

    private Rating atkRating;

    private List<Trait> traits;

    private String name;
}
