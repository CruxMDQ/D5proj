package com.callisto.d5proj.pojos;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 19/05/2015.
 */
public class Feature {

    private String name;
    private String description;
    private boolean hasChoices;
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

    public boolean hasChoices() {
        return hasChoices;
    }

    public void setHasChoices(boolean hasChoices) {
        this.hasChoices = hasChoices;
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
}
