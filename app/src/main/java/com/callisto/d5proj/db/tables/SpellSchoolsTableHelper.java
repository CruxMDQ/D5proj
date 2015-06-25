package com.callisto.d5proj.db.tables;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.util.Pair;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 22/06/2015.
 */
class SpellSchoolsTableHelper extends BaseTableHelper {
    private static final String T_SPELL_SCHOOLS = "SpellSchools";
    private static final String C_SCHOOL_NAME = "schoolName";

    private ArrayList<Pair<Integer, String>> spellSchools;

    public SpellSchoolsTableHelper(Context context) {
        super(context);
        this.setManagedTable(T_SPELL_SCHOOLS);
        this.setColumns(new String[] { C_ID, C_SCHOOL_NAME });

        load();
    }

    private void load() {
        this.spellSchools = new ArrayList<>();

        Cursor cursor = this.getCursor();

        while (cursor.moveToNext()) {
            Pair<Integer, String> spellSchool = new Pair<>(
                cursor.getInt(cursor.getColumnIndexOrThrow(C_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(C_SCHOOL_NAME)));

            spellSchools.add(spellSchool);
        }
    }

    public String getSchoolName(int schoolId) {
        for (Pair<Integer, String> p : spellSchools) {
            if (p.first.equals(schoolId)) {
                return p.second;
            }
        }
        return null;
    }
}
