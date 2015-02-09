package com.callisto.d5proj.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by emiliano.desantis on 02/09/2014.
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    private static final String DB_NAME = "d5proj.sqlite";
    private static final int DB_SCHEME_VERSION = 1;

//    private String T_TYPES_TRAITS = "types_traits",
//        C_ID = "_id",
//        C_TYPE_ID = "id_type",
//        C_TRAIT_ID = "id_trait",
//        C_VALUE = "value",
//        C_DETAIL = "detail";
//
//    private String D_TYPES_TRAITS = "CREATE TABLE" + " " + T_TYPES_TRAITS + "("
//            + C_TYPE_ID + " " + "INTEGER NOT NULL" + ","
//            + C_TRAIT_ID + " " + "INTEGER NOT NULL" + ","
//            + C_VALUE + " " + "INTEGER" + ","
//            + C_DETAIL + " " + "TEXT" + ","
//            + "PRIMARY KEY" + " " + "(" + C_TYPE_ID + "," + C_TRAIT_ID + "),"
//            + "FOREIGN KEY" + " " + "(" + C_TYPE_ID + ")" + " " + "REFERENCES" + " "
//            + DBCreatureTypes.T_CREATURE_TYPES + " " + "(" + C_ID + ")" + " "
//            + "ON UPDATE CASCADE ON DELETE CASCADE" + ","
//            + "FOREIGN KEY" + " " + "(" + C_TRAIT_ID + ")" + " " + "REFERENCES" + " "
//            + DBTraits.T_TRAITS + " " + "(" + C_ID + ")" + " "
//            + "ON UPDATE CASCADE ON DELETE CASCADE"
//            + ")";
}
