package com.callisto.d5proj.db.tables;

import android.content.Context;

/**
 * Created by emiliano.desantis on 08/05/2015.
 */
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
