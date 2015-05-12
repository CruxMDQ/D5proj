package com.callisto.d5proj.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;

/**
 * Created by emiliano.desantis on 19/02/2015.
 */
public class DBHelper extends SQLiteOpenHelper {
    static private DBHelper instance;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);

        try {
            DBUtils.executeSqlScript(context, getWritableDatabase(), "d5Proj.sql");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    private static final String DB_NAME = "d5proj.sqlite";
    private static final int DB_SCHEME_VERSION = 1;

}
