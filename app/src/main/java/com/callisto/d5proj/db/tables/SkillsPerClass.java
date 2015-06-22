package com.callisto.d5proj.db.tables;

import android.content.Context;

/**
 * Created by emiliano.desantis on 13/02/2015.
 */
public class SkillsPerClass extends BaseTableHelper {
    public SkillsPerClass(Context context) {
        super(context);
        this.setManagedTable(T_SKILLS_CLASSES);
        this.setColumns(new String[] { C_ID_CLASS, C_ID_SKILL });
    }

    static public String T_SKILLS_CLASSES = "SkillsPerClass",
        C_ID_CLASS = "id_class",
        C_ID_SKILL = "id_skill";
}
