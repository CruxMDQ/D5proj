package com.callisto.d5proj.db;

import android.content.Context;
import android.database.Cursor;

import com.callisto.d5proj.pojos.CharacterClass;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 09/02/2015.
 */
public class DBCharacterClasses extends DBAdapter {
    public DBCharacterClasses(Context context) {
        super(context);
        this.setManagedTable(T_CHARACTER_CLASSES);
        this.setColumns(new String[]{ C_ID, C_NAME, C_DIE_SIZE, C_SKILLS} );

        create();
    }

    private void create() {
        open();
        db.execSQL(DEFINE_PC_CLASSES);
        db.delete(T_CHARACTER_CLASSES, null, null);

//        db.execSQL(DEFINE_PC_CLASSES_INDEX);
//        db.execSQL(DBKeyStatsPerClass.DEFINE_CLASSES_KEYSTATS);
//        db.execSQL(DEFINE_CLASSES_SAVES);

//        db.delete(DBKeyStatsPerClass.T_CLASSES_KEYSTATS, null, null);
//        db.delete(T_CLASSES_SAVES, null, null);

//        loadCharacterClasses();

//        close();
    }

    // TODO FIND A BETTER WAY TO LOAD STATIC TABLES!
//    private void loadCharacterClasses() {
//        ArrayList<CharacterClass> classes = this.getArrayList();
//
//        ArrayList<BaseStatistic> keys = new ArrayList<>();
//
//        ArrayList<BaseStatistic> saves = new ArrayList<>();
//
//        keys.add(BaseStatistic.STR);
//        saves.add(BaseStatistic.STR);
//        saves.add(BaseStatistic.CON);
//
//        insert(context.getString(R.string.classname_barbarian), 12, keys, saves );
//
//        keys.clear();
//        keys.add(BaseStatistic.CHA);
//
//        saves.clear();
//        saves.add(BaseStatistic.DEX);
//        saves.add(BaseStatistic.CHA);
//
//        insert(context.getString(R.string.classname_bard), 8, keys, saves);
//
//        keys.clear();
//        keys.add(BaseStatistic.WIS);
//
//        saves.clear();
//        saves.add(BaseStatistic.WIS);
//        saves.add(BaseStatistic.CHA);
//
//        insert(context.getString(R.string.classname_cleric), 8, keys, saves);
//
//        keys.clear();
//        keys.add(BaseStatistic.WIS);
//
//        saves.clear();
//        saves.add(BaseStatistic.INT);
//        saves.add(BaseStatistic.WIS);
//
//        insert(context.getString(R.string.classname_druid), 8, keys, saves);
//
//        keys.clear();
//        keys.add(BaseStatistic.STR);
//        keys.add(BaseStatistic.DEX);
//
//        saves.clear();
//        saves.add(BaseStatistic.STR);
//        saves.add(BaseStatistic.CON);
//
//        insert(context.getString(R.string.classname_fighter), 10, keys, saves);
//
//        keys.clear();
//        keys.add(BaseStatistic.WIS);
//        keys.add(BaseStatistic.DEX);
//
//        saves.clear();
//        saves.add(BaseStatistic.STR);
//        saves.add(BaseStatistic.DEX);
//
//        insert(context.getString(R.string.classname_monk), 8, keys, saves);
//
//        keys.clear();
//        keys.add(BaseStatistic.STR);
//        keys.add(BaseStatistic.CHA);
//
//        saves.clear();
//        saves.add(BaseStatistic.WIS);
//        saves.add(BaseStatistic.CHA);
//
//        insert(context.getString(R.string.classname_paladin), 10, keys, saves);
//
//        keys.clear();
//        keys.add(BaseStatistic.WIS);
//        keys.add(BaseStatistic.DEX);
//
//        saves.clear();
//        saves.add(BaseStatistic.STR);
//        saves.add(BaseStatistic.DEX);
//
//        insert(context.getString(R.string.classname_ranger), 10, keys, saves);
//
//        keys.clear();
//        keys.add(BaseStatistic.DEX);
//
//        saves.clear();
//        saves.add(BaseStatistic.INT);
//        saves.add(BaseStatistic.DEX);
//
//        insert(context.getString(R.string.classname_rogue), 8, keys, saves);
//
//        keys.clear();
//        keys.add(BaseStatistic.CHA);
//
//        saves.clear();
//        saves.add(BaseStatistic.CON);
//        saves.add(BaseStatistic.CHA);
//
//        insert(context.getString(R.string.classname_sorcerer), 6, keys, saves);
//
//        keys.clear();
//        keys.add(BaseStatistic.CHA);
//
//        saves.clear();
//        saves.add(BaseStatistic.WIS);
//        saves.add(BaseStatistic.CHA);
//
//        insert(context.getString(R.string.classname_warlock), 8, keys, saves);
//
//        keys.clear();
//        keys.add(BaseStatistic.INT);
//
//        saves.clear();
//        saves.add(BaseStatistic.INT);
//        saves.add(BaseStatistic.WIS);
//
//        insert(context.getString(R.string.classname_wizard), 6, keys, saves);
//    }

    public ArrayList<CharacterClass> getArrayList() {
        ArrayList<CharacterClass> characterClasses = new ArrayList<>();

        Cursor c = this.getCursor();

        c.moveToFirst();

        while(!c.isAfterLast()) {
            CharacterClass characterClass = new CharacterClass();
            characterClass.setName(c.getString(c.getColumnIndexOrThrow(C_NAME)));
            characterClass.setDieSize(c.getInt(c.getColumnIndexOrThrow(C_DIE_SIZE)));
            characterClass.setSkills(c.getInt(c.getColumnIndexOrThrow(C_SKILLS)));

            characterClasses.add(characterClass);
            c.moveToNext();
        }

        return characterClasses;
    }

//    public void insert(String name, int dieSize, ArrayList<BaseStatistic> keyStats, ArrayList<BaseStatistic> saves) {
//        ContentValues reg = new ContentValues();
//
//        reg.put(C_NAME, name);
//        reg.put(C_DIE_SIZE, dieSize);
//
//        long classId = this.insertOrThrow(reg);
//
//        reg.clear();
//
////        db.execSQL("INSERT OR IGNORE INTO " + T_CHARACTER_CLASSES + "(" + C_NAME + ", " + C_DIE_SIZE+ ")"
////            + " VALUES (" + name + ", " + dieSize + ");");
//
//        BaseStatistic stat;
//
//        for (BaseStatistic keyStat : keyStats) {
//            stat = keyStat;
//            reg.clear();
//            reg.put(DBKeyStatsPerClass.C_STAT, stat.toString());
//
//            db.insertOrThrow(T_CLASSES_SAVES, null, reg);
////            db.execSQL("INSERT INTO " + T_CLASSES_KEYSTATS + "(" + C_ID_CLASS + ", " + C_STAT + ")"
////                + " VALUES (" + classId + ", " + "\"" + stat.toString() + "\"" + ");");
//        }
//
//        for (BaseStatistic save : saves) {
//            stat = save;
//            reg.clear();
//            reg.put(DBKeyStatsPerClass.C_STAT, stat.toString());
//
//            db.insertOrThrow(T_CLASSES_SAVES, null, reg);
//
////            db.execSQL("INSERT INTO " + T_CLASSES_SAVES + "(" + C_ID_CLASS + ", " + C_STAT + ")"
////                + " VALUES (" + classId + ", " + "\"" + stat.toString() + "\"" + ");");
//        }
//    }

    /**
     * Table definition: character classes
     */
    static public final String T_CHARACTER_CLASSES = "CharacterClasses",
        C_NAME = "name",
        C_DIE_SIZE = "dieSize",
        C_SKILLS = "skills";

    static public final String DEFINE_PC_CLASSES = "CREATE TABLE IF NOT EXISTS" + " " + T_CHARACTER_CLASSES + "("
        + C_ID + " " + "INTEGER PRIMARY KEY AUTOINCREMENT" + ","
        + C_NAME + " " + "TEXT NOT NULL" + ","
        + C_DIE_SIZE + " " + "INTEGER NOT NULL,"
        + C_SKILLS + " " + "INTEGER NOT NULL,"
        + "UNIQUE (" + C_NAME + ")"
        + ");";

//    static public final String DEFINE_PC_CLASSES_INDEX = "CREATE UNIQUE INDEX IF NOT EXISTS" + " " + "I" + T_CHARACTER_CLASSES
//        + " ON " + T_CHARACTER_CLASSES + "(" + C_ID + " ASC" + ");";

//    /**
//     * Table definition: character class saving throws
//     */
//    static public final String T_CLASSES_SAVES = "classes_keysaves";
//
//    static public final String DEFINE_CLASSES_SAVES = "CREATE TABLE IF NOT EXISTS" + " " + T_CLASSES_SAVES + "("
//        + C_ID + " " + "INTEGER PRIMARY KEY" + ","
//        + DBKeyStatsPerClass.C_ID_CLASS + " " + "INTEGER NOT NULL" + ","
//        + DBKeyStatsPerClass.C_STAT + " " + "TEXT NOT NULL" + ","
//        + " FOREIGN KEY" + "(" + DBKeyStatsPerClass.C_ID_CLASS + ")" + " REFERENCES " + DBCharacterClasses.T_CHARACTER_CLASSES + "(" + C_ID + ") ON DELETE CASCADE"
//        + ");";

}
