package com.callisto.d5proj.db.tables;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.callisto.d5proj.enums.BaseStatistic;
import com.callisto.d5proj.pojos.Feature;
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

    private RacialStatsDBAdapter racialStatsDBAdapter;
    private RacialFeaturesDBAdapter racialFeaturesDBAdapter;

    private List<Race> races;
    private List<Feature> featureList;

    public RacesDBAdapter(Context context) {
        super(context);
        racialStatsDBAdapter = new RacialStatsDBAdapter(context);
        racialFeaturesDBAdapter = new RacialFeaturesDBAdapter(context);
        this.setManagedTable(T_RACES);
        this.setColumns(new String[]{C_ID, C_NAME, C_SPEED, C_ID_SIZE, C_ID_PARENT, C_IS_ARCHETYPE});
        races = getAllRaces();
    }

    public ArrayList<Race> getAllRaces() {
        ArrayList<Race> result = new ArrayList<>();

        Cursor races = this.getCursor();
        Cursor raceStats = racialStatsDBAdapter.getCursor();

        while (races.moveToNext()) {
            Race race = new Race();

            race.setName(races.getString(races.getColumnIndexOrThrow(C_NAME)));

            int raceId = races.getInt(races.getColumnIndexOrThrow(C_ID));

            race.setId(raceId);

            while (raceStats.moveToNext()) {
                if (raceId == raceStats.getInt(raceStats.getColumnIndexOrThrow(RacialStatsDBAdapter.C_ID_RACE))) {
                    Pair<BaseStatistic, Integer> statMod = new Pair<>(
                        (BaseStatistic.valueOf(raceStats.getString(raceStats.getColumnIndexOrThrow(
                            RacialStatsDBAdapter.C_STAT)))),
                        raceStats.getInt(raceStats.getColumnIndexOrThrow(RacialStatsDBAdapter.C_BONUS))
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

            try {
                race.setRacialFeatures(racialFeaturesDBAdapter.getRacialFeatures(raceId));
            } catch (NullPointerException e) {
                Toast.makeText(context, "Features list not loaded!", Toast.LENGTH_SHORT);
            }
            result.add(race);
        }

        return result;
    }

    public List<Race> getRaces() {
        return races;
    }

    public void setFeatureList(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public List<Feature> getFeatureList() {
        return featureList;
    }

    public class RacialStatsDBAdapter extends BaseTableAdapter {

        static public final String T_RACIAL_STATS = "RacialStats";
        static public final String C_ID_RACE = "id_race";
        static public final String C_STAT = "stat";
        static public final String C_BONUS = "bonus";

        public RacialStatsDBAdapter(Context context) {
            super(context);
            this.setManagedTable(T_RACIAL_STATS);
            this.setColumns(new String[] { C_ID, C_ID_RACE, C_STAT, C_BONUS });
        }
    }

    public class RacialFeaturesDBAdapter extends BaseTableAdapter {

        static public final String T_RACIAL_FEATURES = "RacialFeatures";
        static public final String C_ID_RACE = "id_race";
        static public final String C_ID_FEATURE = "id_feature";

        List<Feature> featureList;

        public RacialFeaturesDBAdapter(Context context) {
            super(context);
            this.setManagedTable(T_RACIAL_FEATURES);
            this.setColumns(new String[] { C_ID, C_ID_RACE, C_ID_FEATURE });
        }

        public void setFeatureList(List<Feature> featureList) {
            this.featureList = featureList;
        }

        public ArrayList<Feature> getRacialFeatures(int idRace) {
            if (featureList == null) throw new NullPointerException("Features list required!");
            else {

                ArrayList<Feature> result = new ArrayList<>();

                Cursor racialFeatures = this.getCursor();

                while (racialFeatures.moveToNext()) {
                    int lookUp = racialFeatures
                        .getInt(racialFeatures.getColumnIndexOrThrow(C_ID_RACE));

                    if (idRace == lookUp) {
                        Iterator<Feature> I = featureList.iterator();

                        while (I.hasNext()) {
                            Feature f = I.next();

                            if (f.getId() == racialFeatures.getInt(racialFeatures.getColumnIndexOrThrow(C_ID_FEATURE))) {
                                result.add(f);
                            }
                        }
                    }
                }

                return result;
            }
        }
    }

}
