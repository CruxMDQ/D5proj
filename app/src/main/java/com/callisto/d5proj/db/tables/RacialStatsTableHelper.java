package com.callisto.d5proj.db.tables;

import android.content.Context;

/**
 * Created by emiliano.desantis on 08/05/2015.
 */
class RacialStatsTableHelper extends BaseTableHelper {

    private static final String T_RACIAL_STATS = "RacialStats";
    private static final String C_ID_RACE = "id_race";
    private static final String C_STAT = "stat";
    private static final String C_BONUS = "bonus";

    public RacialStatsTableHelper(Context context) {
        super(context);
        this.setManagedTable(T_RACIAL_STATS);
        this.setColumns(new String[] { C_ID, C_ID_RACE, C_STAT, C_BONUS });
    }
}
