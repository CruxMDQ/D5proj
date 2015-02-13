package com.callisto.d5proj.db.tables;

import android.content.Context;

/**
 * Created by emiliano.desantis on 02/09/2014.
 */
public class BaseCreatureTypes extends BaseTableAdapter {

    public BaseCreatureTypes(Context context) {
        super(context);
        this.setManagedTable(T_CREATURE_TYPES);
        this.setKeyColumn(C_ID);
        this.setColumns(new String[]{C_ID,
                C_STR, C_DEX, C_CON, C_INT, C_WIS, C_CHA,
                C_DIE_SIZE, C_SKILLS, C_ATK_RATING, C_NAME});
    }

    public static String getDefinition() {
        return "CREATE TABLE" + " " + T_CREATURE_TYPES + "("
            + C_ID + " " + "INTEGER PRIMARY KEY" + ","
            + C_STR + " " + "INTEGER NOT NULL" + ","
            + C_DEX + " " + "INTEGER NOT NULL" + ","
            + C_CON + " " + "INTEGER NOT NULL" + ","
            + C_INT + " " + "INTEGER NOT NULL" + ","
            + C_WIS + " " + "INTEGER NOT NULL" + ","
            + C_CHA + " " + "INTEGER NOT NULL" + ","
            + C_DIE_SIZE + " " + "INTEGER NOT NULL" + ","
            + C_SKILLS + " " + "INTEGER NOT NULL" + ","
            + C_ATK_RATING + " " + "TEXT NOT NULL" + ","
            + C_NAME + " " + "TEXT NOT NULL"
            + ");";
    }

    public static String getIndex() {
        return "CREATE UNIQUE INDEX" + " " + C_ID
            + " " + "ON" + " " + T_CREATURE_TYPES
            + "(" + C_ID + " " + "ASC)";
    }

    static public final String T_CREATURE_TYPES = "creatureTypes",
            C_ID = "_id",
            C_STR = "str",
            C_DEX = "dex",
            C_CON = "con",
            C_INT = "int",
            C_WIS = "wis",
            C_CHA = "cha",
            C_DIE_SIZE = "dieSize",
            C_SKILLS = "skills",
            C_ATK_RATING = "atkRating",
            C_NAME = "name";

}
