package com.callisto.d5proj.db;

import android.content.Context;

/**
 * Created by emiliano.desantis on 02/09/2014.
 */
public class DBTraits extends DBAdapter{
    public DBTraits(Context context) {
        super(context);
        this.setManagedTable(T_TRAITS);
        this.setKeyColumn(C_ID);
        this.setColumns(new String[] { C_ID, C_NAME });
    }

    static public final String T_TRAITS = "traits",
            C_ID = "_id",
            C_NAME = "name";

    static public final String DEFINITION = "CREATE TABLE" + " " + T_TRAITS + "("
            + C_ID + " " + "INTEGER PRIMARY KEY" + ","
            + C_NAME + " " + "TEXT NOT NULL"
            + ");";

    static public final String INDEX = "CREATE UNIQUE INDEX" + " " + C_ID
            + " " + "ON" + " " + T_TRAITS
            + "(" + C_ID + " " + "ASC)";
}
