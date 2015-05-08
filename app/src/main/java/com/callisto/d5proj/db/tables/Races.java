package com.callisto.d5proj.db.tables;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.util.Pair;

import com.callisto.d5proj.enums.BaseStatistic;
import com.callisto.d5proj.pojos.Race;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 08/05/2015.
 */
public class Races extends BaseTableAdapter {

    static public final String T_RACES = "Races";
    static public final String C_NAME = "name";
    static public final String C_SPEED = "speed";
    static public final String C_ID_SIZE = "id_size";
    static public final String C_ID_PARENT = "id_parent";
    static public final String C_IS_ARCHETYPE = "isArchetype";

    private RacialStats racialStats;

    public Races(Context context) {
        super(context);
        racialStats = new RacialStats(context);
        this.setManagedTable(T_RACES);
        this.setColumns(new String[] { C_ID, C_NAME, C_SPEED, C_ID_SIZE, C_ID_PARENT, C_IS_ARCHETYPE });
    }

    public ArrayList<Race> getAllRaces() {
        ArrayList<Race> result = new ArrayList<>();

        Cursor races = this.getCursor();
        Cursor raceStats = racialStats.getCursor();

        while (races.moveToNext()) {
            Race race = new Race();

            race.setName(races.getString(races.getColumnIndexOrThrow(C_NAME)));

            int raceId = races.getInt(races.getColumnIndexOrThrow(C_ID));

            while (raceStats.moveToNext()) {
                if (raceId == raceStats.getInt(raceStats.getColumnIndexOrThrow(RacialStats.C_ID_RACE))) {
                    Pair<BaseStatistic, Integer> statMod = new Pair<>(
                        (BaseStatistic.valueOf(raceStats.getString(raceStats.getColumnIndexOrThrow(RacialStats.C_STAT)))),
                        raceStats.getInt(raceStats.getColumnIndexOrThrow(RacialStats.C_BONUS))
                    );

                    race.addStatMod(statMod);

                    raceStats.moveToFirst();
                    break;
                }
            }
        }

        return result;
    }
}
