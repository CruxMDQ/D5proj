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
import com.callisto.d5proj.interfaces.OnSpellPickedListener;
import com.callisto.d5proj.pojos.Feature;
import com.callisto.d5proj.pojos.Spell;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 10/02/2015.
 */
public class PickSpellsDialogFragment extends android.support.v4.app.DialogFragment
    implements OnFeaturePickedListener, OnSpellPickedListener{

    public PickSpellsDialogFragment() { }

    public static PickSpellsDialogFragment newInstance(Feature feature, int level, String casterClass, AfterChoosingOptionsListener listener)
    {
        PickSpellsDialogFragment frag = new PickSpellsDialogFragment();
        frag.listener = listener;
        frag.feature = feature;
        frag.isModal = true;

        frag.spellLevel = level;
        frag.casterClass = casterClass;
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
            switch(spellLevel) {
                case 0: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_one_zero));
                    break;
                }
                case 1: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_one_one));
                    break;
                }
                case 2: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_one_two));
                    break;
                }
                case 3: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_one_three));
                    break;
                }
                case 4: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_one_four));
                    break;
                }
                case 5: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_one_five));
                    break;
                }
                case 6: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_one_six));
                    break;
                }
                case 7: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_one_seven));
                    break;
                }
                case 8: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_one_eight));
                    break;
                }
                case 9: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_one_nine));
                    break;
                }
            }
        } else {
            switch(spellLevel) {
                case 0: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_several_zero, feature.getChoices()));
                    break;
                }
                case 1: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_several_one, feature.getChoices()));
                    break;
                }
                case 2: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_several_two, feature.getChoices()));
                    break;
                }
                case 3: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_several_three, feature.getChoices()));
                    break;
                }
                case 4: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_several_four, feature.getChoices()));
                    break;
                }
                case 5: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_several_five, feature.getChoices()));
                    break;
                }
                case 6: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_several_six, feature.getChoices()));
                    break;
                }
                case 7: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_several_seven, feature.getChoices()));
                    break;
                }
                case 8: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_several_eight, feature.getChoices()));
                    break;
                }
                case 9: {
                    alertDialogBuilder.setTitle(getString(R.string.spells_pick_several_nine, feature.getChoices()));
                    break;
                }
            }
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
    public void onSpellPicked(Spell spell) {

    }
    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
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
    private ArrayList<Spell> spells;

    private boolean isModal = false;

    private AlertDialog dialog;

    private int spellLevel;
    private String casterClass;
}
