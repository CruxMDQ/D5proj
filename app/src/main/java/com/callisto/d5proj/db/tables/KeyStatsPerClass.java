package com.callisto.d5proj.db.tables;

import android.content.Context;

/**
 * Created by emiliano.desantis on 12/02/2015.
 */
class KeyStatsPerClass extends BaseTableHelper {
    /**
     * Table definition: character class key statistics
     */
    private static final String T_CLASSES_KEYSTATS = "KeyStatsPerClass";
    private static final String C_ID_CLASS = "id_class";
    private static final String C_STAT = "stat";
    private static final String C_TYPE = "type";
//    static public final String V_PRIMARY = "Primary";
//    static public final String V_SAVE = "Save";
//    static public final String DEFINE_CLASSES_KEYSTATS = "CREATE TABLE IF NOT EXISTS" + " " + T_CLASSES_KEYSTATS + "("
//        + C_ID + " " + "INTEGER PRIMARY KEY AUTOINCREMENT" + ","
//        + C_ID_CLASS + " " + "INTEGER NOT NULL" + ","
//        + C_STAT + " " + "TEXT NOT NULL" + ","
//        + C_TYPE + " " + "TEXT NOT NULL CHECK (" + C_TYPE + " IN('" + V_PRIMARY + "', '" + V_SAVE + "')),"
//        + " FOREIGN KEY" + "(" + C_ID_CLASS + ")" + " REFERENCES " + DBCharacterClasses.T_CHARACTER_CLASSES + "(" + C_ID + ") ON UPDATE CASCADE ON DELETE CASCADE"
//        + ");";

    public KeyStatsPerClass(Context context) {
        super(context);
        this.setManagedTable(T_CLASSES_KEYSTATS);
        this.setColumns(new String[] { C_ID, C_ID_CLASS, C_STAT, C_TYPE });
    }
}
