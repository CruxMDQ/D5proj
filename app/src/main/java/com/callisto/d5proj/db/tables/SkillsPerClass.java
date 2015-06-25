package com.callisto.d5proj.db.tables;

import android.content.Context;

/**
 * Created by emiliano.desantis on 13/02/2015.
 */
class SkillsPerClass extends BaseTableHelper {
    public SkillsPerClass(Context context) {
        super(context);
        this.setManagedTable(T_SKILLS_CLASSES);
        this.setColumns(new String[] { C_ID_CLASS, C_ID_SKILL });
    }

    private static String T_SKILLS_CLASSES = "SkillsPerClass";
    private static String C_ID_CLASS = "id_class";
    private static String C_ID_SKILL = "id_skill";
}
