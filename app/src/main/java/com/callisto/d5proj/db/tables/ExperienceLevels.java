package com.callisto.d5proj.db.tables;

import android.content.Context;

/**
 * Created by emiliano.desantis on 09/02/2015.
 */
public class ExperienceLevels extends BaseTableHelper {
    public ExperienceLevels(Context context) {
        super(context);
        this.setManagedTable(T_EXP_LEVELS);
        this.setColumns(new String[] { C_ID, C_XP, C_BONUS });

    }

    /**
     * Base experience table stuff
     */
    private static String T_EXP_LEVELS = "experienceLevels";
    private static String C_XP = "xp";
    private static String C_BONUS = "profBonus";

    static public String DEFINE_EXP_LEVELS = "CREATE TABLE IF NOT EXISTS " + T_EXP_LEVELS + "("
        + C_ID + " " + "INTEGER PRIMARY KEY" + ","
        + C_XP + " " + "INTEGER NOT NULL" + ","
        + C_BONUS + " " + "INTEGER NOT NULL"
        + ");";

    /**
     * Features table stuff
     */
    private static String T_FEATURES = "features";
    private static String C_NAME = "name";

    static public String DEFINE_FEATURES = "CREATE TABLE IF NOT EXISTS " + T_FEATURES + "("
        + C_ID + " " + "INTEGER PRIMARY KEY" + ","
        + C_NAME + " " + "TEXT NOT NULL"
        + ");";

    /**
     * Classes / features / levels
     */
    private static String T_CLASSES_FEATURES = "classesFeatures";
    private static String C_ID_CLASS = "id_class";
    private static String C_ID_FEATURE = "id_feature";
    private static String C_LEVEL = "level";

    static public String DEFINE_CLASSES_FEATURES = "CREATE TABLE IF NOT EXISTS" + " " + T_CLASSES_FEATURES + "("
        + C_ID_CLASS + " INTEGER NOT NULL,"
        + C_ID_FEATURE + " INTEGER NOT NULL,"
        + C_LEVEL + " INTEGER,"
        + " PRIMARY KEY (" + C_ID_CLASS + ", " + C_ID_FEATURE + "),"
        + " FOREIGN KEY (" + C_ID_CLASS + ")" + " REFERENCES " + CharacterClassesHelper.T_CHARACTER_CLASSES + "(" + C_ID + ")" + " ON UPDATE CASCADE ON DELETE CASCADE,"
        + " FOREIGN KEY (" + C_ID_FEATURE + ")" + " REFERENCES " + T_FEATURES + "(" + C_ID + ")" + " ON UPDATE CASCADE ON DELETE CASCADE"
        + ");";
}
