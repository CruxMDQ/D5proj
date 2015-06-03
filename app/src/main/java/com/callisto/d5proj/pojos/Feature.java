package com.callisto.d5proj.pojos;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 19/05/2015.
 */
@SuppressWarnings("unused")
public class Feature {

    private int id;
    private String name;
    private String description;
    private int choices;
    private ArrayList<Feature> featureChoices;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChoices() {
        return choices;
    }

    public void setChoices(int choices) {
        this.choices = choices;
    }

    public void addFeatureChoice(Feature feature) {
        if (featureChoices == null) {
            featureChoices = new ArrayList<>();
        }

        featureChoices.add(feature);
    }

    public ArrayList<Feature> getFeatureChoices() {
        if (featureChoices != null) {
            return featureChoices;
        } else {
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
