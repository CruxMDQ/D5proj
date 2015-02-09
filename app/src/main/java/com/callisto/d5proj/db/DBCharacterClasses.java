package com.callisto.d5proj.db;

import android.content.ContentValues;
import android.content.Context;

import com.callisto.d5proj.enums.BaseStatistic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by emiliano.desantis on 09/02/2015.
 */
public class DBCharacterClasses extends DBAdapter {
    public DBCharacterClasses(Context context) {
        super(context);
        this.setManagedTable(T_CHARACTER_CLASSES);
        this.setColumns(new String[]{ C_ID, C_NAME, C_DIE_SIZE} );

        create();
    }

    private void create() {
        open();
        db.execSQL(DEFINE_PC_CLASSES);
        db.execSQL(DEFINE_PC_CLASSES_INDEX);
        db.execSQL(DEFINE_CLASSES_KEYSTATS);
        db.execSQL(DEFINE_CLASSES_SAVES);

        db.delete(T_CHARACTER_CLASSES, null, null);
        db.delete(T_CLASSES_KEYSTATS, null, null);
        db.delete(T_CLASSES_SAVES, null, null);
        
        ArrayList<BaseStatistic> keys = new ArrayList<>();
        keys.add(BaseStatistic.STR);
        
        ArrayList<BaseStatistic> saves = new ArrayList<>();
        saves.add(BaseStatistic.STR);
        saves.add(BaseStatistic.CON);
        
        insert("Barbarian", 12, keys, new ArrayList<BaseStatistic>() {} );

        close();
    }

    public void insert(String name, int dieSize, ArrayList<BaseStatistic> keyStats, ArrayList<BaseStatistic> saves) {
        ContentValues reg = new ContentValues();

        reg.put(C_NAME, name);
        reg.put(C_DIE_SIZE, dieSize);

        long classId = this.insert(reg);

        reg.clear();

        open();

        BaseStatistic stat;

        Iterator<BaseStatistic> stats = keyStats.iterator();

        while (stats.hasNext()) {
            stat = stats.next();

            db.execSQL("INSERT INTO " + T_CLASSES_KEYSTATS + "(" + C_ID_CLASS + ", " + C_STAT + ")"
                + " VALUES (" + classId + ", " + "\"" + stat.toString() + "\"" + ");");
        }

        Iterator<BaseStatistic> savingThrows = saves.iterator();

        while (savingThrows.hasNext()) {
            stat = savingThrows.next();

            db.execSQL("INSERT INTO " + T_CLASSES_SAVES + "(" + C_ID_CLASS + ", " + C_STAT + ")"
                + " VALUES (" + classId + ", " + "\"" + stat.toString() + "\"" + ");");
        }

        close();
    }

    /**
     * Table definition: character classes
     */
    static public final String T_CHARACTER_CLASSES = "characterClasses",
        C_NAME = "name",
        C_DIE_SIZE = "dieSize";

    static public final String DEFINE_PC_CLASSES = "CREATE TABLE IF NOT EXISTS" + " " + T_CHARACTER_CLASSES + "("
        + C_ID + " " + "INTEGER PRIMARY KEY" + ","
        + C_NAME + " " + "TEXT NOT NULL" + ","
        + C_DIE_SIZE + " " + "INTEGER NOT NULL"
        + ");";

    static public final String DEFINE_PC_CLASSES_INDEX = "CREATE UNIQUE INDEX IF NOT EXISTS" + " " + "I" + T_CHARACTER_CLASSES
        + " ON " + T_CHARACTER_CLASSES + "(" + C_ID + " ASC" + ");";

    /**
     * Table definition: character class key statistics
     */
    static public final String T_CLASSES_KEYSTATS = "classes_keystats",
        C_ID_CLASS = "id_class",
        C_STAT = "stat";

    static public final String DEFINE_CLASSES_KEYSTATS = "CREATE TABLE IF NOT EXISTS" + " " + T_CLASSES_KEYSTATS + "("
        + C_ID_CLASS + " " + "INTEGER PRIMARY KEY" + ","
        + C_STAT + " " + "TEXT NOT NULL" + ","
        + " FOREIGN KEY" + "(" + C_ID_CLASS + ")" + " REFERENCES " + DBCharacterClasses.T_CHARACTER_CLASSES + "(" + C_ID + ") ON DELETE CASCADE"
        + ");";

    /**
     * Table definition: character class saving throws
     */
    static public final String T_CLASSES_SAVES = "classes_keysaves";

    static public final String DEFINE_CLASSES_SAVES = "CREATE TABLE IF NOT EXISTS" + " " + T_CLASSES_SAVES + "("
        + C_ID_CLASS + " " + "INTEGER PRIMARY KEY" + ","
        + C_STAT + " " + "TEXT NOT NULL" + ","
        + " FOREIGN KEY" + "(" + C_ID_CLASS + ")" + " REFERENCES " + DBCharacterClasses.T_CHARACTER_CLASSES + "(" + C_ID + ") ON DELETE CASCADE"
        + ");";

}
