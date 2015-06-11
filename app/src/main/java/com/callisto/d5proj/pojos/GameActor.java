package com.callisto.d5proj.pojos;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 07/05/2015.
 */
public class GameActor {
    private int STR;
    private int DEX;
    private int INT;
    private int CON;
    private int WIS;
    private int CHA;
    private int xp;
    private int hitPoints;
    private int armorClass;
    private Race race;
    private ArrayList<Feature> features;
    private ArrayList<ClassLevel> classLevels;

    public GameActor() { }

    public GameActor(int armorClass, int CHA, int CON, int DEX, int hitPoints, int INT, int STR, int WIS, int xp) {
        this.armorClass = armorClass;
        this.CHA = CHA;
        this.CON = CON;
        this.DEX = DEX;
        this.hitPoints = hitPoints;
        this.INT = INT;
        this.STR = STR;
        this.WIS = WIS;
        this.xp = xp;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public int getCHA() {
        return CHA;
    }

    public void setCHA(int CHA) {
        this.CHA = CHA;
    }

    public ArrayList<ClassLevel> getClassLevels() {
        return classLevels;
    }

    public void setClassLevels(ArrayList<ClassLevel> classLevels) {
        this.classLevels = classLevels;
    }

    public int getCON() {
        return CON;
    }

    public void setCON(int CON) {
        this.CON = CON;
    }

    public int getDEX() {
        return DEX;
    }

    public void setDEX(int DEX) {
        this.DEX = DEX;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints() {

        for (ClassLevel classLevel : classLevels) {
            hitPoints += classLevel.getHpRoll();
        }
    }

    public void forceHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getINT() {
        return INT;
    }

    public void setINT(int INT) {
        this.INT = INT;
    }

    public int getSTR() {
        return STR;
    }

    public void setSTR(int STR) {
        this.STR = STR;
    }

    public int getWIS() {
        return WIS;
    }

    public void setWIS(int WIS) {
        this.WIS = WIS;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }
}
