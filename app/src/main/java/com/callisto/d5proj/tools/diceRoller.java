package com.callisto.d5proj.tools;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Toolkit for handling dice rolls.
 * Created by emiliano.desantis on 08/05/2015.
 */
public class DiceRoller {

    static private Random mRand = new Random();

    static public int rollDice(int dice, int size, int discard, int modifier) {
        int total = rollDice(dice, size, discard);

        total += modifier;

        return total;
    }

    static public int rollDice(int dice, int size, int discard) {
        int total = 0;

        Integer[] rolls = new Integer[dice];

        // Source: http://stackoverflow.com/questions/363681/
        for (int i = 0; i < dice; i++) {
            rolls[i] = DiceRoller.randInt(size);
        }

        // Source: http://stackoverflow.com/questions/8938235/
        Arrays.sort(rolls, Collections.reverseOrder());

        for (int i = 0; i < (dice - discard); i++) {
            total += rolls[i];
        }

        return total;
    }

    static public int randInt(int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
//        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return mRand.nextInt((max - 1) + 1) + 1;
    }

}
