package com.callisto.d5proj.db.tables;

import android.content.Context;
import android.database.Cursor;

import com.callisto.d5proj.pojos.Spell;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 22/06/2015.
 */
public class SpellsTableHelper extends BaseTableHelper {
    private static final String T_SPELLS = "Spells";
    private static final String C_NAME = "name";
    private static final String C_ID_SCHOOL = "id_school";
    private static final String C_LEVEL = "level";
    private static final String C_CASTING_TIME = "castingTime";
    private static final String C_IS_INSTANTANEOUS = "isInstantaneous";
    private static final String C_REQ_CONCENTRATION = "requiresConcentration";
    private static final String C_COMP_HAS_VERBAL = "hasVerbalComponent";
    private static final String C_COMP_HAS_SOMATIC = "hasSomaticComponent";
    private static final String C_COMP_HAS_MATERIAL = "hasMaterialComponent";
    private static final String C_IS_COMBAT_SPELL = "isCombatSpell";
    private static final String C_DETAIL = "detail";

    private SpellSchoolsTableHelper spellSchoolsTableHelper;

    private ArrayList<Spell> spells;

    public SpellsTableHelper(Context context) {
        super(context);
        this.setManagedTable(T_SPELLS);
        this.setColumns(
            new String[]{C_ID, C_NAME, C_ID_SCHOOL, C_LEVEL, C_CASTING_TIME, C_IS_INSTANTANEOUS, C_REQ_CONCENTRATION, C_COMP_HAS_VERBAL, C_COMP_HAS_SOMATIC, C_COMP_HAS_MATERIAL, C_IS_COMBAT_SPELL, C_DETAIL});

        spellSchoolsTableHelper = new SpellSchoolsTableHelper(context);

        load();
    }

    private void load() {
        this.spells = new ArrayList<>();

        Cursor cursor = this.getCursor();

        while (cursor.moveToNext()) {
            Spell spell = new Spell();

            setSpellValues(spell, cursor);

            spells.add(spell);
        }
    }

    private void setSpellValues(Spell spell, Cursor cursor) {
        spell.setName(cursor.getString(cursor.getColumnIndexOrThrow(SpellsTableHelper.C_NAME)));
        spell.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(SpellsTableHelper.C_LEVEL)));
        spell.setSchool(spellSchoolsTableHelper.getSchoolName(cursor.getInt(cursor.getColumnIndexOrThrow(SpellsTableHelper.C_ID_SCHOOL))));
        spell.setCastingTime(cursor.getInt(cursor.getColumnIndexOrThrow(C_CASTING_TIME)));
        spell.setIsInstantaneous(cursor.getInt(cursor.getColumnIndexOrThrow(C_IS_INSTANTANEOUS)) == 1);
        spell.setRequiresConcentration(cursor.getInt(cursor.getColumnIndexOrThrow(C_REQ_CONCENTRATION)) == 1);
        spell.setHasVerbalComponent(cursor.getInt(cursor.getColumnIndexOrThrow(C_COMP_HAS_VERBAL)) == 1);
        spell.setHasSomaticComponent(cursor.getInt(cursor.getColumnIndexOrThrow(C_COMP_HAS_SOMATIC)) == 1);
        spell.setHasMaterialComponent(cursor.getInt(cursor.getColumnIndexOrThrow(C_COMP_HAS_MATERIAL)) == 1);
        spell.setIsCombatSpell(cursor.getInt(cursor.getColumnIndexOrThrow(C_IS_COMBAT_SPELL)) == 1);
        spell.setDetail(cursor.getString(cursor.getColumnIndexOrThrow(C_DETAIL)));
    }

    public ArrayList<Spell> filterSpellsPerLevel(int level) {
        if (spells == null) {
            load();
        }

        ArrayList<Spell> result = new ArrayList<>();

        for (Spell s : spells) {
            if (s.getLevel() == level) {
                result.add(s);
            }
        }

        return result;
    }
}
