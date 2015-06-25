package com.callisto.d5proj.fragments.dialogs;

import android.app.AlertDialog;

import com.callisto.d5proj.R;
import com.callisto.d5proj.adapters.SpellChoicesRVAdapter;
import com.callisto.d5proj.interfaces.AfterChoosingOptionsListener;
import com.callisto.d5proj.pojos.Feature;
import com.callisto.d5proj.pojos.Spell;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 10/02/2015.
 */
public class PickSpellsDialogFragment extends PickOptionDialogFragment //android.support.v4.app.DialogFragment
    //implements OnFeaturePickedListener, OnSpellPickedListener{
{
    public PickSpellsDialogFragment() { }

    public static PickSpellsDialogFragment newInstance(Feature feature, ArrayList<Spell> spells, int spellLevel, AfterChoosingOptionsListener listener) {
        PickSpellsDialogFragment frag = new PickSpellsDialogFragment();
        frag.listener = listener;
        frag.feature = feature;
        frag.isModal = true;

        frag.spells = spells;
        frag.spellLevel = spellLevel;

        return frag;
    }

    protected void setDialogTitle(AlertDialog.Builder alertDialogBuilder) {
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
    }

    protected void populateChoices() {
        rvChoices.setAdapter(new SpellChoicesRVAdapter(spells, this));
    }

    @Override
    protected void addPick(Object pick) {
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

    private ArrayList<Spell> spells;

    private int spellLevel;
//    private String casterClass;
}
