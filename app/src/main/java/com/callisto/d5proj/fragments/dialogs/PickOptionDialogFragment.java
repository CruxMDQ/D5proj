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
import com.callisto.d5proj.interfaces.AfterChoosingFeatureOptionsListener;
import com.callisto.d5proj.interfaces.OnChoicePickedListener;
import com.callisto.d5proj.pojos.Feature;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 25/06/2015.
 */
public abstract class PickOptionDialogFragment<T> extends android.support.v4.app.DialogFragment implements OnChoicePickedListener {
    RecyclerView rvChoices;
    Feature feature;
    AfterChoosingFeatureOptionsListener<T> listener;
    ArrayList<T> picks;
    boolean isModal = false;
    AlertDialog dialog;

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

        setDialogTitle(alertDialogBuilder);

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                fireOnClick(feature, picks);
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
    }

    @Override
    public void onChoicePicked(Object pick) {
        addPick(pick);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
//        super.getDialog().getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }

    private void findComponents(View rootView) {
        rvChoices = (RecyclerView) rootView.findViewById(R.id.rvFeatureChoices);
        rvChoices.setLayoutManager(new LinearLayoutManager(getActivity()));
        populateChoices();
    }

    protected abstract void setDialogTitle(AlertDialog.Builder alertDialogBuilder);

    protected abstract void populateChoices();

    protected abstract void addPick(Object pick);

//    protected abstract void fireOnClick(Object feature, ArrayList<T> picks);

    @Override
    public AlertDialog getDialog() {
        return dialog;
    }

    boolean isModal() {
        return isModal;
    }

    public void setModal(boolean isModal) {
        this.isModal = isModal;
    }
}
