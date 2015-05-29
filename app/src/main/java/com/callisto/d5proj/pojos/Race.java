package com.callisto.d5proj.pojos;

import android.support.v4.util.Pair;

import com.callisto.d5proj.enums.BaseStatistic;

import java.util.ArrayList;

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
        return statModifiers;
    }

    public void setRacialFeatures(ArrayList<Feature> racialFeatures) {
        this.racialFeatures = racialFeatures;
    }

    public ArrayList<Feature> getRacialFeatures() { return racialFeatures; }

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
}
