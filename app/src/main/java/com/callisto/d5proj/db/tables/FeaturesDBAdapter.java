package com.callisto.d5proj.db.tables;

import android.content.Context;
import android.database.Cursor;

import com.callisto.d5proj.pojos.Feature;

import java.util.ArrayList;
import java.util.List;

/**
 * Internal table adapter for retrieval of features.
 * Created by emiliano.desantis on 19/05/2015.
 */
public class FeaturesDBAdapter extends BaseTableAdapter {

    static public final String T_FEATURES = "Features";
    static public final String C_NAME = "name";
    static public final String C_HAS_OPTIONS = "hasOptions";
    static public final String C_DESCRIPTION = "description";

    private List<Feature> features;

    public FeaturesDBAdapter(Context context) {
        super(context);
        this.setManagedTable(T_FEATURES);
        this.setColumns(new String[]{C_ID, C_NAME, C_HAS_OPTIONS, C_DESCRIPTION});
    }

    public ArrayList<Feature> getAllFeatures() {
        ArrayList<Feature> result = new ArrayList<>();

        Cursor features = this.getCursor();

        while (features.moveToNext()) {
            Feature feature = new Feature();

            feature.setId(features.getInt(features.getColumnIndexOrThrow(C_ID)));
            feature.setName(features.getString(features.getColumnIndexOrThrow(C_NAME)));
            feature.setHasChoices(features.getInt(features.getColumnIndexOrThrow(C_HAS_OPTIONS)) == 1);
            feature.setDescription(features.getString(features.getColumnIndexOrThrow(C_DESCRIPTION)));

            result.add(feature);
        }

        return result;
    }

}
