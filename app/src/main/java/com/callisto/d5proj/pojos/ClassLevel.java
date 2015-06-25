package com.callisto.d5proj.pojos;

import com.callisto.d5proj.tools.DiceRoller;

/**
 * Created by emiliano.desantis on 08/05/2015.
 */
@SuppressWarnings("unused")
class ClassLevel {

    private CharacterClass characterClass;
    private int hpRoll;
    private int skillPoints;

    public ClassLevel() { }

    public ClassLevel(CharacterClass characterClass, int hpRoll, int skillPoints) {
        this.characterClass = characterClass;
        this.hpRoll = hpRoll;
        this.skillPoints = skillPoints;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public int getHpRoll() {
        return hpRoll;
    }

    public void setHpRoll(int hpRoll) {
        this.hpRoll = hpRoll;
    }

    public void rollHp() {
        this.hpRoll = DiceRoller.randInt(characterClass.getDieSize());
    }

    public int getSkillPoints() {
        return characterClass.getSkills();
    }

    public void setSkillPoints() {
        this.skillPoints = characterClass.getSkills();
    }
}
