package com.callisto.d5proj.pojos;

import com.callisto.d5proj.enums.BaseStatistic;

import java.util.ArrayList;

/**
 * Created by Crux on 08/02/2015.
 */
public class CharacterClass {
    String name;
    int dieSize;
    int skills;
    ArrayList<BaseStatistic> keyStats;
    ArrayList<BaseStatistic> savingThrowProficiencies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<BaseStatistic> getKeyStats() {
        return keyStats;
    }

    public void setKeyStats(ArrayList<BaseStatistic> keyStats) {
        this.keyStats = keyStats;
    }

    public ArrayList<BaseStatistic> getSavingThrowProficiencies() {
        return savingThrowProficiencies;
    }

    public void setSavingThrowProficiencies(ArrayList<BaseStatistic> savingThrowProficiencies) {
        this.savingThrowProficiencies = savingThrowProficiencies;
    }
}
