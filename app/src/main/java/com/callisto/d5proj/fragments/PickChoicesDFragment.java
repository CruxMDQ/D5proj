package com.callisto.d5proj.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.callisto.d5proj.R;
import com.callisto.d5proj.adapters.FeatureChoicesRVAdapter;
import com.callisto.d5proj.pojos.Feature;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 10/02/2015.
 */
public class PickChoicesDFragment extends android.support.v4.app.DialogFragment {

    public PickChoicesDFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pick_feature_choices, container, false);

        findComponents(rootView);

        showClassSelectorDialog();

        return rootView;
    }

    private void findComponents(View rootView) {

    }

    public void showClassSelectorDialog() {
        if (options == null) throw new NullPointerException(getString(R.string.error_options_array_null));

        LayoutInflater li = LayoutInflater.from(getActivity());

        View promptsView = li.inflate(R.layout.dialog_choose_feature_option, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setView(promptsView);

        if (options.size() == 1) {
            alertDialogBuilder.setTitle(getString(R.string.feature_options_pick_one));
        } else {
            alertDialogBuilder.setTitle(
                String.format(getString(R.string.feature_options_pick_several), options.size()));
        }

        alertDialogBuilder.setIcon(R.drawable.ic_launcher);
        // create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();

        rvFeatureChoices = (RecyclerView) promptsView.findViewById(R.id.rvFeatureChoices);

        final Button mButton = (Button) promptsView
            .findViewById(R.id.btnOK);

        populateFeatureChoices();

        // show it
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);
    }

    private void populateFeatureChoices() {
         rvFeatureChoices.setAdapter(new FeatureChoicesRVAdapter(getActivity(), options));
    }

    public ArrayList<Feature> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Feature> options) {
        this.options = options;
    }

    private RecyclerView rvFeatureChoices;

    private ArrayList<Feature> options;
}
