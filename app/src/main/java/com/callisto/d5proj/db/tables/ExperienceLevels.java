package com.callisto.d5proj.db.tables;

import android.content.Context;
import android.util.Log;

import com.callisto.d5proj.db.DBUtils;

import java.io.IOException;

/**
 * Created by emiliano.desantis on 09/02/2015.
 */
public class ExperienceLevels extends BaseTableAdapter {
    public ExperienceLevels(Context context) {
        super(context);
        this.setManagedTable(T_EXP_LEVELS);
        this.setColumns(new String[] { C_ID, C_XP, C_BONUS });

        create();
    }

    private void create() {
        open();
        db.execSQL(DEFINE_EXP_LEVELS);

        db.delete(T_EXP_LEVELS, null, null);

        try {
            DBUtils.executeSqlScript(context, db, "ExperienceLevels.sql");
        } catch (IOException e) {
            Log.e("DBCharacterLevels.java", "Error on importing SQL script");
        }

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
        + " FOREIGN KEY (" + C_ID_CLASS + ")" + " REFERENCES " + CharacterClasses.T_CHARACTER_CLASSES + "(" + C_ID + ")" + " ON UPDATE CASCADE ON DELETE CASCADE,"
        + " FOREIGN KEY (" + C_ID_FEATURE + ")" + " REFERENCES " + T_FEATURES + "(" + C_ID + ")" + " ON UPDATE CASCADE ON DELETE CASCADE"
        + ");";
}
