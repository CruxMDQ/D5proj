package com.callisto.d5proj.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.callisto.d5proj.R;
import com.callisto.d5proj.adapters.FeatureChoicesRVAdapter;
import com.callisto.d5proj.interfaces.AfterChoosingOptionsListener;
import com.callisto.d5proj.interfaces.OnFeaturePickedListener;
import com.callisto.d5proj.pojos.Feature;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 10/02/2015.
 */
public class PickChoicesDialogFragment extends android.support.v4.app.DialogFragment
    implements OnFeaturePickedListener  {

    public PickChoicesDialogFragment() { }

    public static PickChoicesDialogFragment newInstance(Feature feature, AfterChoosingOptionsListener listener)
    {
        PickChoicesDialogFragment frag = new PickChoicesDialogFragment();
        frag.listener = listener;
        frag.feature = feature;
        frag.isModal = true;
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.isModal()) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            View rootView = inflater
                .inflate(R.layout.fragment_pick_feature_choices, container, false);

            findComponents(rootView);

            return rootView;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder;

        alertDialogBuilder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_choose_feature_option, null);

        alertDialogBuilder.setView(view);

        if (feature.getChoices() == 1) {
            alertDialogBuilder.setTitle(getString(R.string.feature_options_pick_one));
        } else {
            alertDialogBuilder.setTitle(
                String.format(getString(R.string.feature_options_pick_several), feature.getChoices()));
        }

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.afterChoosingOptions(feature, picks);
                dialog.dismiss();
            }
        });

        findComponents(view);

        dialog = alertDialogBuilder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button positive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positive.setEnabled(false);
            }
        });

        return dialog;
//        return alertDialogBuilder.create();
    }

    @Override
    public void onFeaturePicked(Feature pick) {
        addPick(pick);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
//        super.getDialog().getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }

    private void findComponents(View rootView) {
        rvFeatureChoices = (RecyclerView) rootView.findViewById(R.id.rvFeatureChoices);
        rvFeatureChoices.setLayoutManager(new LinearLayoutManager(getActivity()));
        mButton = (Button) rootView.findViewById(R.id.btnOK);
        populateFeatureChoices();
    }

    private void populateFeatureChoices() {
        rvFeatureChoices.setAdapter(new FeatureChoicesRVAdapter(feature, this));
    }

    public void addPick(Feature pick) {
        if (picks == null) picks = new ArrayList<>();
        if (picks.contains(pick)) {
            picks.remove(pick);
        } else {
            picks.add(pick);
        }

        if (picks.size() != feature.getChoices()) {
            getDialog().getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        } else {
            getDialog().getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
        }
    }

    @Override
    public AlertDialog getDialog() {
        return dialog;
    }

    public boolean isModal() {
        return isModal;
    }

    public void setModal(boolean isModal) {
        this.isModal = isModal;
    }

    private RecyclerView rvFeatureChoices;
    private Button mButton;

    private Feature feature;
    private AfterChoosingOptionsListener listener;
    private ArrayList<Feature> picks;

    private boolean isModal = false;

    private AlertDialog dialog;
}
