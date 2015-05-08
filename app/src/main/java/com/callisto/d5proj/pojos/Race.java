package com.callisto.d5proj.pojos;

import android.support.v4.util.Pair;

import com.callisto.d5proj.enums.BaseStatistic;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 08/05/2015.
 */
public class Race {

    private String name;
    private ArrayList<Pair<BaseStatistic, Integer>> statModifiers;

    public Race() { }

    public Race(String name) {
        this.name = name;
        statModifiers = new ArrayList<>();
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
}
