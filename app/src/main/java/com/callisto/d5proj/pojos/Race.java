package com.callisto.d5proj.pojos;

import android.support.v4.util.Pair;

import com.callisto.d5proj.enums.BaseStatistic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by emiliano.desantis on 08/05/2015.
 */
public class Race {

    private int id;
    private String name;
    private Race parent;
    private ArrayList<Pair<BaseStatistic, Integer>> statModifiers;
    private ArrayList<Feature> racialFeatures;

    public Race() {
        statModifiers = new ArrayList<>();
        racialFeatures = new ArrayList<>();
    }

    public Race(String name) {
        this.name = name;
        statModifiers = new ArrayList<>();
        racialFeatures = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStatMod(Pair<BaseStatistic, Integer> statMod) {
        statModifiers.add(statMod);
    }

    public ArrayList<Pair<BaseStatistic, Integer>> getStatModifiers() {
        if (parent == null) return statModifiers;
        else {
            ArrayList<Pair<BaseStatistic, Integer>> result = new ArrayList<>();

            result.addAll(statModifiers);
            result.addAll(parent.getStatModifiers());

            return result;
        }
    }

    public void setRacialFeatures(ArrayList<Feature> racialFeatures) {
        this.racialFeatures = racialFeatures;

        sortFeatures(racialFeatures);
    }

    public ArrayList<Feature> getRacialFeatures() {
        if (parent == null) {
            return racialFeatures;
        } else {
            ArrayList<Feature> result = new ArrayList<>();

            result.addAll(racialFeatures);
            result.addAll(parent.getRacialFeatures());

            sortFeatures(result);

            return result;
        }
    }

    public Race getParent() {
        return parent;
    }

    public void setParent(Race parent) {
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void sortFeatures(ArrayList<Feature> racialFeatures) {
        Collections.sort(racialFeatures, new Comparator<Feature>() {
            @Override
            public int compare(Feature f1, Feature f2) {
                return f1.getName().compareToIgnoreCase(f2.getName());
            }
        });
    }
}
