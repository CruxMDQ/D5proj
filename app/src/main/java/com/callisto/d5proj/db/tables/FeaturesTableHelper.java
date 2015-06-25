package com.callisto.d5proj.db.tables;

import android.content.Context;
import android.database.Cursor;

import com.callisto.d5proj.pojos.Feature;

import java.util.ArrayList;

/**
 * Internal table adapter for retrieval of features.
 * Created by emiliano.desantis on 19/05/2015.
 */
class FeaturesTableHelper extends BaseTableHelper {

    private static final String T_FEATURES = "Features";
    private static final String C_NAME = "name";
    private static final String C_HAS_OPTIONS = "hasOptions";
    private static final String C_DESCRIPTION = "description";

    private FeaturesWithOptionsDBHelper featuresWithOptionsDBAdapter;
    private FeatureChoicesDBHelper featureChoicesDBAdapter;

    private ArrayList<Feature> features;

    public FeaturesTableHelper(Context context) {
        super(context);
        this.setManagedTable(T_FEATURES);
        this.setColumns(new String[]{C_ID, C_NAME, C_HAS_OPTIONS, C_DESCRIPTION});

        featureChoicesDBAdapter = new FeatureChoicesDBHelper(context);
        featuresWithOptionsDBAdapter = new FeaturesWithOptionsDBHelper(context);

        load();
    }

    private void load() {
        this.features = new ArrayList<>();

        Cursor cursor = this.getCursor();

        while (cursor.moveToNext()) {
            Feature feature = new Feature();

            setFeatureValues(feature, cursor);

            features.add(feature);
        }

        for (Feature feature : features) {
            if (feature.getChoices() > 0) {
                Cursor featuresWithOptionsCursor = featuresWithOptionsDBAdapter.getCursor();

                while (featuresWithOptionsCursor.moveToNext()) {
                    int featureCode = featuresWithOptionsCursor.getInt(featuresWithOptionsCursor
                        .getColumnIndexOrThrow(FeaturesWithOptionsDBHelper.C_ID_FEATURE));

                    int choices = featuresWithOptionsCursor.getInt(featuresWithOptionsCursor
                        .getColumnIndexOrThrow(FeaturesWithOptionsDBHelper.C_CHOICES));

                    if (feature.getId() == featureCode) {
                        feature.setChoices(choices);
                        break;
                    }
                }

                Cursor featureChoicesCursor = featureChoicesDBAdapter.getCursor();

                while (featureChoicesCursor.moveToNext()) {
                    int featureId = featureChoicesCursor.getInt(featureChoicesCursor.getColumnIndexOrThrow(
                        FeatureChoicesDBHelper.C_ID_FEATURE));
                    int choiceId = featureChoicesCursor.getInt(featureChoicesCursor
                        .getColumnIndexOrThrow(FeatureChoicesDBHelper.C_ID_CHOICE));
                    if (featureId == feature.getId()) {
                        for (Feature potentialChoice : features) {
                            if (potentialChoice.getId() == choiceId) {
                                feature.addFeatureChoice(potentialChoice);
                            }
                        }
                    }
                }
            }
        }
    }

    private void setFeatureValues(Feature feature, Cursor cursor) {
        feature.setId(cursor.getInt(cursor.getColumnIndexOrThrow(C_ID)));
        feature.setName(cursor.getString(cursor.getColumnIndexOrThrow(C_NAME)));
        feature.setChoices(cursor.getInt(cursor.getColumnIndexOrThrow(C_HAS_OPTIONS)));
        feature.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(C_DESCRIPTION)));
    }

    public ArrayList<Feature> resetFeatures() {
        load();
        return features;
    }

    public ArrayList<Feature> getAllFeatures() {
        return features;
    }

    class FeaturesWithOptionsDBHelper extends BaseTableHelper {

        static public final String T_FEATURES_WITH_OPTIONS = "FeaturesWithOptions";
        static public final String C_ID_FEATURE = "id_feature";
        static public final String C_CHOICES = "choices";
        static public final String C_ROW_ID = "rowid";

        public FeaturesWithOptionsDBHelper(Context context) {
            super(context);
            this.setManagedTable(T_FEATURES_WITH_OPTIONS);
            this.setColumns(new String[] { C_ID_FEATURE, C_CHOICES });
        }
    }

    class FeatureChoicesDBHelper extends BaseTableHelper {

        static public final String T_FEATURES_CHOICES = "FeatureChoices";
        static public final String C_ID_FEATURE = "id_feature";
        static public final String C_ID_CHOICE = "id_choice";

        public FeatureChoicesDBHelper(Context context) {
            super(context);
            this.setManagedTable(T_FEATURES_CHOICES);
            this.setColumns(new String[] { C_ID_FEATURE, C_ID_CHOICE});
        }
    }
}
