package com.callisto.d5proj.pojos;

import android.support.v4.util.Pair;

import com.callisto.d5proj.enums.BaseStatistic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

/**
 * Created by emiliano.desantis on 08/05/2015.
 */
@SuppressWarnings("unused")
public class Race implements Serializable {

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

        sortFeatures(this.racialFeatures);
    }

    public ArrayList<Feature> getRacialFeatures() {
        if (parent == null) {
            return racialFeatures;
        } else {
            ArrayList<Feature> result = new ArrayList<>();

            HashSet<Feature> set = new HashSet<>();

            result.addAll(racialFeatures);
            result.addAll(parent.getRacialFeatures());

            set.addAll(result);

            result.clear();

            result.addAll(set);

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

    public ArrayList<String> getAbilityScoreModifiers() {
        ArrayList<String> result = new ArrayList<>();

        for (Pair<BaseStatistic, Integer> statMod : getStatModifiers()) {
            String stat = "+" + statMod.second.toString()
                + " " + statMod.first.toString();

            result.add(stat);
        }

        return result;
    }

    public ArrayList<String> getFeatureBreakdown() {
        ArrayList<String> result = new ArrayList<>();

//        Iterator<Pair<BaseStatistic, Integer>> I = getStatModifiers().iterator();
//
//        while (I.hasNext()) {
//            Pair<BaseStatistic, Integer> statMod = I.next();
//
//            String stat = "+" + statMod.second.toString()
//                + " " + statMod.first.toString();
//
//            result.add(stat);
//        }

        for (Feature f : getRacialFeatures()) {
            if (!result.contains(f.getName())) {
                result.add(f.getName());
            }
        }

        return result;
    }
}
