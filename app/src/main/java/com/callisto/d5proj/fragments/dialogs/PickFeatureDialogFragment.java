package com.callisto.d5proj.fragments.dialogs;

import android.app.AlertDialog;

import com.callisto.d5proj.R;
import com.callisto.d5proj.adapters.FeatureChoicesRVAdapter;
import com.callisto.d5proj.interfaces.AfterChoosingFeatureOptionsListener;
import com.callisto.d5proj.pojos.Feature;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 10/02/2015.
 */
public class PickFeatureDialogFragment extends PickOptionDialogFragment {

    public PickFeatureDialogFragment() { }

    public static PickFeatureDialogFragment newInstance(Feature feature, AfterChoosingFeatureOptionsListener listener)
    {
        PickFeatureDialogFragment frag = new PickFeatureDialogFragment();
        frag.listener = listener;
        frag.feature = feature;
        frag.isModal = true;
        return frag;
    }

    @Override
    protected void setDialogTitle(AlertDialog.Builder alertDialogBuilder) {
        if (feature.getChoices() == 1) {
            alertDialogBuilder.setTitle(getString(R.string.feature_options_pick_one));
        } else {
            alertDialogBuilder.setTitle(
                String.format(getString(R.string.feature_options_pick_several), feature.getChoices()));
        }
    }

    @Override
    protected void populateChoices() {
        rvChoices.setAdapter(new FeatureChoicesRVAdapter(feature, this));
    }

    @Override
    protected void addPick(Object pick) {
        if (pick instanceof Feature) {
            if (picks == null) picks = new ArrayList<>();
            if (picks.contains(pick)) {
                picks.remove(pick);
            } else {
                picks.add(pick);
            }

            if (picks.size() != feature.getChoices()) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            } else {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            }
        }
    }
}
