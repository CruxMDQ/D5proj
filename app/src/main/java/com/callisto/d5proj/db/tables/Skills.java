package com.callisto.d5proj.db.tables;

import android.content.Context;

/**
 * Created by emiliano.desantis on 13/02/2015.
 */
@SuppressWarnings("unused")
class Skills extends BaseTableHelper {
    public Skills(Context context) {
        super(context);
        this.setManagedTable(T_SKILLS);
        this.setColumns(new String[] { C_ID, C_NAME, C_KEYSTAT});
    }

    private static String T_SKILLS = "Skills";
    private static String C_NAME = "name";
    private static String C_KEYSTAT = "keyStat";
}
