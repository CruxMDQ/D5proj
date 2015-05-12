package com.callisto.d5proj.db.tables;

import android.content.Context;
import android.database.Cursor;

import com.callisto.d5proj.pojos.CharacterClass;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 09/02/2015.
 */
public class CharacterClassesAdapter extends BaseTableAdapter {
    public CharacterClassesAdapter(Context context) {
        super(context);
        this.setManagedTable(T_CHARACTER_CLASSES);
        this.setColumns(new String[]{ C_ID, C_NAME, C_DIE_SIZE, C_SKILLS} );

//        create();
    }

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

}
