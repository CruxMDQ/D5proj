package com.callisto.d5proj.pojos;

/**
 * Created by emiliano.desantis on 22/06/2015.
 */
public class Spell {
    private String name;
    private int level;
    private String school;
    private int castingTime;
    private boolean isInstantaneous;
    private boolean requiresConcentration;
    private boolean hasVerbalComponent;
    private boolean hasSomaticComponent;
    private boolean hasMaterialComponent;
    private boolean isCombatSpell;
    private String detail;

    public Spell() { }

    public Spell(int castingTime, String detail, boolean hasMaterialComponent, boolean hasSomaticComponent, boolean hasVerbalComponent, int idSchool, boolean isCombatSpell, boolean isInstantaneous, int level, boolean requiresConcentration) {
        this.castingTime = castingTime;
        this.detail = detail;
        this.hasMaterialComponent = hasMaterialComponent;
        this.hasSomaticComponent = hasSomaticComponent;
        this.hasVerbalComponent = hasVerbalComponent;
        this.isCombatSpell = isCombatSpell;
        this.isInstantaneous = isInstantaneous;
        this.level = level;
        this.requiresConcentration = requiresConcentration;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(int castingTime) {
        this.castingTime = castingTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isHasMaterialComponent() {
        return hasMaterialComponent;
    }

    public void setHasMaterialComponent(boolean hasMaterialComponent) {
        this.hasMaterialComponent = hasMaterialComponent;
    }

    public boolean isHasSomaticComponent() {
        return hasSomaticComponent;
    }

    public void setHasSomaticComponent(boolean hasSomaticComponent) {
        this.hasSomaticComponent = hasSomaticComponent;
    }

    public boolean isHasVerbalComponent() {
        return hasVerbalComponent;
    }

    public void setHasVerbalComponent(boolean hasVerbalComponent) {
        this.hasVerbalComponent = hasVerbalComponent;
    }

    public boolean isCombatSpell() {
        return isCombatSpell;
    }

    public void setIsCombatSpell(boolean isCombatSpell) {
        this.isCombatSpell = isCombatSpell;
    }

    public boolean isInstantaneous() {
        return isInstantaneous;
    }

    public void setIsInstantaneous(boolean isInstantaneous) {
        this.isInstantaneous = isInstantaneous;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isRequiresConcentration() {
        return requiresConcentration;
    }

    public void setRequiresConcentration(boolean requiresConcentration) {
        this.requiresConcentration = requiresConcentration;
    }
}
