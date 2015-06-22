package com.callisto.d5proj.db.tables;

import android.content.Context;

/**
 * Created by emiliano.desantis on 08/05/2015.
 */
public class Sizes extends BaseTableHelper {

    static public final String T_SIZES = "Sizes";
    static public final String C_NAME = "name";

    public Sizes(Context context) {
        super(context);
        this.setManagedTable(T_SIZES);
        this.setColumns(new String[] { C_ID, C_NAME });
    }
}
