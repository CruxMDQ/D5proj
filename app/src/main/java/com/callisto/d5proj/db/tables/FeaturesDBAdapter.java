package com.callisto.d5proj.db.tables;

import android.content.Context;
import android.database.Cursor;

import com.callisto.d5proj.pojos.Feature;

import java.util.ArrayList;

/**
 * Internal table adapter for retrieval of features.
 * Created by emiliano.desantis on 19/05/2015.
 */
public class FeaturesDBAdapter extends BaseTableAdapter {

    static public final String T_FEATURES = "Features";
    static public final String C_NAME = "name";
    static public final String C_HAS_OPTIONS = "hasOptions";
    static public final String C_DESCRIPTION = "description";

    private FeaturesWithOptionsDBAdapter featuresWithOptionsDBAdapter;
    private FeatureChoicesDBAdapter featureChoicesDBAdapter;

    private ArrayList<Feature> features;

    public FeaturesDBAdapter(Context context) {
        super(context);
        this.setManagedTable(T_FEATURES);
        this.setColumns(new String[]{C_ID, C_NAME, C_HAS_OPTIONS, C_DESCRIPTION});

        featureChoicesDBAdapter = new FeatureChoicesDBAdapter(context);
        featuresWithOptionsDBAdapter = new FeaturesWithOptionsDBAdapter(context);

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
                        .getColumnIndexOrThrow(FeaturesWithOptionsDBAdapter.C_ID_FEATURE));

                    int choices = featuresWithOptionsCursor.getInt(featuresWithOptionsCursor
                        .getColumnIndexOrThrow(FeaturesWithOptionsDBAdapter.C_CHOICES));

                    if (feature.getId() == featureCode) {
                        feature.setChoices(choices);
                        break;
                    }
                }

                Cursor featureChoicesCursor = featureChoicesDBAdapter.getCursor();

                while (featureChoicesCursor.moveToNext()) {
                    int featureId = featureChoicesCursor.getInt(featureChoicesCursor.getColumnIndexOrThrow(FeatureChoicesDBAdapter.C_ID_FEATURE));
                    int choiceId = featureChoicesCursor.getInt(featureChoicesCursor
                        .getColumnIndexOrThrow(FeatureChoicesDBAdapter.C_ID_CHOICE));
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

    class FeaturesWithOptionsDBAdapter extends BaseTableAdapter {

        static public final String T_FEATURES_WITH_OPTIONS = "FeaturesWithOptions";
        static public final String C_ID_FEATURE = "id_feature";
        static public final String C_CHOICES = "choices";
        static public final String C_ROW_ID = "rowid";

        public FeaturesWithOptionsDBAdapter(Context context) {
            super(context);
            this.setManagedTable(T_FEATURES_WITH_OPTIONS);
            this.setColumns(new String[] { C_ID_FEATURE, C_CHOICES });
        }
    }

    class FeatureChoicesDBAdapter extends BaseTableAdapter {

        static public final String T_FEATURES_CHOICES = "FeatureChoices";
        static public final String C_ID_FEATURE = "id_feature";
        static public final String C_ID_CHOICE = "id_choice";

        public FeatureChoicesDBAdapter(Context context) {
            super(context);
            this.setManagedTable(T_FEATURES_CHOICES);
            this.setColumns(new String[] { C_ID_FEATURE, C_ID_CHOICE});
        }
    }
}
