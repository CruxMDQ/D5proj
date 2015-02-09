package com.callisto.d5proj.db;

import android.content.Context;

/**
 * Created by emiliano.desantis on 09/02/2015.
 */
public class DBCharacterLevels extends DBAdapter {
    public DBCharacterLevels(Context context) {
        super(context);

        create();
    }

    private void create() {
        open();
        db.execSQL(DEFINE_EXP_LEVELS);

        db.delete(T_EXP_LEVELS, null, null);

        insertBaseLevel(1, 0, 2);
        insertBaseLevel(0, 300, 2);
        insertBaseLevel(0, 900, 2);
        insertBaseLevel(0, 2700, 2);
        insertBaseLevel(0, 6500, 3);    // 5
        insertBaseLevel(0, 14000, 3);
        insertBaseLevel(0, 23000, 3);
        insertBaseLevel(0, 34000, 3);
        insertBaseLevel(0, 48000, 4);   // 9
        insertBaseLevel(0, 64000, 4);
        insertBaseLevel(0, 85000, 4);
        insertBaseLevel(0, 100000, 4);
        insertBaseLevel(0, 120000, 5);  // 13
        insertBaseLevel(0, 140000, 5);
        insertBaseLevel(0, 165000, 5);
        insertBaseLevel(0, 195000, 5);
        insertBaseLevel(0, 225000, 6);  // 17
        insertBaseLevel(0, 265000, 6);
        insertBaseLevel(0, 305000, 6);
        insertBaseLevel(0, 355000, 6);

        db.execSQL(DEFINE_FEATURES);
        db.execSQL(DEFINE_CLASSES_FEATURES);

        close();
    }

    /**
     * Base experience table stuff
     */
    static public String T_EXP_LEVELS = "experienceLevels",
        C_XP = "xp",
        C_BONUS = "profBonus";

    static public String DEFINE_EXP_LEVELS = "CREATE TABLE IF NOT EXISTS " + T_EXP_LEVELS + "("
        + C_ID + " " + "INTEGER PRIMARY KEY" + ","
        + C_XP + " " + "INTEGER NOT NULL" + ","
        + C_BONUS + " " + "INTEGER NOT NULL"
        + ");";

    private void insertBaseLevel(int level, long xp, int bonus) {
        db.execSQL("INSERT INTO " + T_EXP_LEVELS + "(" + (level != 0 ? C_ID + ", " + C_XP + ", " + C_BONUS : C_XP + ", " + C_BONUS) + ") "
            + "VALUES (" + (level != 0 ? level + ", " + xp + ", " + bonus : xp + ", " + bonus) + ");");
    }

    /**
     * Features table stuff
     */
    static public String T_FEATURES = "features",
        C_NAME = "name";

    static public String DEFINE_FEATURES = "CREATE TABLE IF NOT EXISTS " + T_FEATURES + "("
        + C_ID + " " + "INTEGER PRIMARY KEY" + ","
        + C_NAME + " " + "TEXT NOT NULL"
        + ");";

    /**
     * Classes / features / levels
     */
    static public String T_CLASSES_FEATURES = "classesFeatures",
        C_ID_CLASS = "id_class",
        C_ID_FEATURE = "id_feature",
        C_LEVEL = "level";

    static public String DEFINE_CLASSES_FEATURES = "CREATE TABLE IF NOT EXISTS" + " " + T_CLASSES_FEATURES + "("
        + C_ID_CLASS + " INTEGER NOT NULL,"
        + C_ID_FEATURE + " INTEGER NOT NULL,"
        + C_LEVEL + " INTEGER,"
        + " PRIMARY KEY (" + C_ID_CLASS + ", " + C_ID_FEATURE + "),"
        + " FOREIGN KEY (" + C_ID_CLASS + ")" + " REFERENCES " + DBCharacterClasses.T_CHARACTER_CLASSES + "(" + C_ID + ")" + " ON UPDATE CASCADE ON DELETE CASCADE,"
        + " FOREIGN KEY (" + C_ID_FEATURE + ")" + " REFERENCES " + T_FEATURES + "(" + C_ID + ")" + " ON UPDATE CASCADE ON DELETE CASCADE"
        + ");";
}
