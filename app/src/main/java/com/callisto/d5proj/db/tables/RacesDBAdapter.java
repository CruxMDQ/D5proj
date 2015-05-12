package com.callisto.d5proj.db.tables;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.util.Pair;

import com.callisto.d5proj.enums.BaseStatistic;
import com.callisto.d5proj.pojos.Race;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by emiliano.desantis on 08/05/2015.
 */
public class RacesDBAdapter extends BaseTableAdapter {

    static public final String T_RACES = "Races";
    static public final String C_NAME = "name";
    static public final String C_SPEED = "speed";
    static public final String C_ID_SIZE = "id_size";
    static public final String C_ID_PARENT = "id_parent";
    static public final String C_IS_ARCHETYPE = "isArchetype";

    private RacialStats racialStats;

    private List<Race> races;

    public RacesDBAdapter(Context context) {
        super(context);
        racialStats = new RacialStats(context);
        this.setManagedTable(T_RACES);
        this.setColumns(new String[]{C_ID, C_NAME, C_SPEED, C_ID_SIZE, C_ID_PARENT, C_IS_ARCHETYPE});
        races = getAllRaces();
    }

    public ArrayList<Race> getAllRaces() {
        ArrayList<Race> result = new ArrayList<>();

        Cursor races = this.getCursor();
        Cursor raceStats = racialStats.getCursor();

        while (races.moveToNext()) {
            Race race = new Race();

            race.setName(races.getString(races.getColumnIndexOrThrow(C_NAME)));

            int raceId = races.getInt(races.getColumnIndexOrThrow(C_ID));

            race.setId(raceId);

            while (raceStats.moveToNext()) {
                if (raceId == raceStats.getInt(raceStats.getColumnIndexOrThrow(RacialStats.C_ID_RACE))) {
                    Pair<BaseStatistic, Integer> statMod = new Pair<>(
                        (BaseStatistic.valueOf(raceStats.getString(raceStats.getColumnIndexOrThrow(RacialStats.C_STAT)))),
                        raceStats.getInt(raceStats.getColumnIndexOrThrow(RacialStats.C_BONUS))
                    );

                    race.addStatMod(statMod);
                }
            }

            raceStats.moveToFirst();

            Integer parentRaceId = races.getInt(races.getColumnIndexOrThrow(C_ID_PARENT));

            if (result.size() != 0) {
                Iterator i = result.iterator();

                while (i.hasNext()) {
                    Race parent = (Race) i.next();

                    if (parent.getId() == parentRaceId) {
                        race.setParent(parent);

                        break;
                    }
                }
            }
//            if (!parentRaceId.equals(0)) {
//                Cursor c = this.getRecord(parentRaceId);
//
//                Race parent = new Race();
//
//                parent.setName(c.getString(c.getColumnIndexOrThrow(C_NAME)));
//
//                while (raceStats.moveToNext()) {
//                    if (parentRaceId == raceStats.getInt(raceStats.getColumnIndexOrThrow(RacialStats.C_ID_RACE))) {
//                        Pair<BaseStatistic, Integer> statMod = new Pair<>(
//                            (BaseStatistic.valueOf(raceStats.getString(raceStats.getColumnIndexOrThrow(RacialStats.C_STAT)))),
//                            raceStats.getInt(raceStats.getColumnIndexOrThrow(RacialStats.C_BONUS))
//                        );
//
//                        race.addStatMod(statMod);
//                        parent.addStatMod(statMod);
//                    }
//                }
//
//                race.setParent(parent);
//
//                raceStats.moveToFirst();
//            }

            result.add(race);
        }

        return result;
    }

    public List<Race> getRaces() {
        return races;
    }
}
