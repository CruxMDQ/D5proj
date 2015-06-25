package com.callisto.d5proj.db.tables;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.util.Pair;
import android.util.Log;

import com.callisto.d5proj.enums.BaseStatistic;
import com.callisto.d5proj.pojos.Feature;
import com.callisto.d5proj.pojos.Race;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by emiliano.desantis on 08/05/2015.
 */
public class RacesTableHelper extends BaseTableHelper {

    private static final String T_RACES = "Races";
    private static final String C_NAME = "name";
    private static final String C_SPEED = "speed";
    private static final String C_ID_SIZE = "id_size";
    private static final String C_ID_PARENT = "id_parent";
    private static final String C_IS_ARCHETYPE = "isArchetype";

    private RacialStatsTableHelper racialStatsDBAdapter;
    private RacialFeaturesTableHelper racialFeaturesDBAdapter;
    private FeaturesTableHelper featuresDBAdapter;

    private List<Race> races;
    private List<Feature> featureList;

    public RacesTableHelper(Context context) {
        super(context);
        racialStatsDBAdapter = new RacialStatsTableHelper(context);
        racialFeaturesDBAdapter = new RacialFeaturesTableHelper(context);
        featuresDBAdapter = new FeaturesTableHelper(context);
        this.setManagedTable(T_RACES);
        this.setColumns(new String[]{C_ID, C_NAME, C_SPEED, C_ID_SIZE, C_ID_PARENT, C_IS_ARCHETYPE});
    }

    public ArrayList<Race> getAllRaces() {
        setFeatureList(featuresDBAdapter.getAllFeatures());

        ArrayList<Race> result = new ArrayList<>();

        Cursor races = this.getCursor();
        Cursor raceStats = racialStatsDBAdapter.getCursor();

        while (races.moveToNext()) {
            Race race = new Race();

            race.setName(races.getString(races.getColumnIndexOrThrow(C_NAME)));

            int raceId = races.getInt(races.getColumnIndexOrThrow(C_ID));

            race.setId(raceId);

            while (raceStats.moveToNext()) {
                if (raceId == raceStats.getInt(raceStats.getColumnIndexOrThrow(
                    RacialStatsTableHelper.C_ID_RACE))) {
                    Pair<BaseStatistic, Integer> statMod = new Pair<>(
                        (BaseStatistic.valueOf(raceStats.getString(raceStats.getColumnIndexOrThrow(
                            RacialStatsTableHelper.C_STAT)))),
                        raceStats.getInt(raceStats.getColumnIndexOrThrow(RacialStatsTableHelper.C_BONUS))
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
                Log.e(this.getClass().toString(), "Feature list not loaded!");
            }
            result.add(race);
        }

        return result;
    }

    public List<Race> getRaces() {
        return races;
    }

    private void setFeatureList(List<Feature> featureList) {
        this.featureList = featureList;
        racialFeaturesDBAdapter.featureList = featureList;
    }

    public List<Feature> getFeatureList() {
        return featureList;
    }

    public class RacialStatsTableHelper extends BaseTableHelper {

        static public final String T_RACIAL_STATS = "RacialStats";
        static public final String C_ID_RACE = "id_race";
        static public final String C_STAT = "stat";
        static public final String C_BONUS = "bonus";

        public RacialStatsTableHelper(Context context) {
            super(context);
            this.setManagedTable(T_RACIAL_STATS);
            this.setColumns(new String[] { C_ID, C_ID_RACE, C_STAT, C_BONUS });
        }
    }

    public class RacialFeaturesTableHelper extends BaseTableHelper {

        static public final String T_RACIAL_FEATURES = "RacialFeatures";
        static public final String C_ID_RACE = "id_race";
        static public final String C_ID_FEATURE = "id_feature";

        List<Feature> featureList;

        public RacialFeaturesTableHelper(Context context) {
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

                        for (Feature f : featureList) {
                            if (f.getId() == racialFeatures
                                .getInt(racialFeatures.getColumnIndexOrThrow(C_ID_FEATURE))) {
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
