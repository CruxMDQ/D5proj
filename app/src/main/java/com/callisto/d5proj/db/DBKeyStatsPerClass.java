package com.callisto.d5proj.db;

import android.content.Context;

/**
 * Created by emiliano.desantis on 12/02/2015.
 */
public class DBKeyStatsPerClass extends DBAdapter {
    /**
     * Table definition: character class key statistics
     */
    static public final String T_CLASSES_KEYSTATS = "KeyStatsPerClass";
    static public final String C_ID_CLASS = "id_class";
    static public final String C_STAT = "stat";
    static public final String C_TYPE = "type";
    static public final String V_PRIMARY = "Primary";
    static public final String V_SAVE = "Save";
    static public final String DEFINE_CLASSES_KEYSTATS = "CREATE TABLE IF NOT EXISTS" + " " + T_CLASSES_KEYSTATS + "("
        + C_ID + " " + "INTEGER PRIMARY KEY AUTOINCREMENT" + ","
        + C_ID_CLASS + " " + "INTEGER NOT NULL" + ","
        + C_STAT + " " + "TEXT NOT NULL" + ","
        + C_TYPE + " " + "TEXT NOT NULL CHECK (" + C_TYPE + " IN('" + V_PRIMARY + "', '" + V_SAVE + "')),"
        + " FOREIGN KEY" + "(" + C_ID_CLASS + ")" + " REFERENCES " + DBCharacterClasses.T_CHARACTER_CLASSES + "(" + C_ID + ") ON UPDATE CASCADE ON DELETE CASCADE"
        + ");";

    public DBKeyStatsPerClass(Context context) {
        super(context);
        this.setManagedTable(T_CLASSES_KEYSTATS);
        this.setColumns(new String[] { C_ID, C_ID_CLASS, C_STAT, C_TYPE });

        create();
    }

    private void create() {
        db.execSQL(DEFINE_CLASSES_KEYSTATS);
        db.delete(T_CLASSES_KEYSTATS, null, null);
    }
}
