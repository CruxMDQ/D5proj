package com.callisto.d5proj.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.callisto.d5proj.Constants;
import com.callisto.d5proj.R;
import com.callisto.d5proj.interfaces.OnInputClickListener;

/**
 * Created by Crux on 21/07/2015.
 */
public class StatStepSettingsDialogFragment extends android.app.DialogFragment {

    private int dice = Constants.DEFAULT_DICE;
    private int extraRolls = Constants.DEFAULT_EXTRA_ROLLS;
    private int pointPool = Constants.DEFAULT_POINT_POOL;

    private CheckBox chkUseStdScores;
    private CheckBox chkRoll;
    private CheckBox chkPointBuy;
    private CheckBox chkManual;

    private OnInputClickListener listener;

    public static StatStepSettingsDialogFragment newInstance(OnInputClickListener listener) {
        StatStepSettingsDialogFragment fragment = new StatStepSettingsDialogFragment();
        fragment.listener = listener;
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Define and retrieve widgets
        View view = inflater.inflate(R.layout.dialog_statalloc_settings, null);

        chkUseStdScores = (CheckBox) view.findViewById(R.id.chkUseStdScores);
        chkRoll = (CheckBox) view.findViewById(R.id.chkRoll);
        chkPointBuy = (CheckBox) view.findViewById(R.id.chkPointBuy);
        chkManual = (CheckBox) view.findViewById(R.id.chkManual);
        final CheckBox chkAllowEdition = (CheckBox) view.findViewById(R.id.chkAllowEdition);

        final LinearLayout panelCustomizeRolls = (LinearLayout) view
            .findViewById(R.id.panelCustomizeRolls);
        final TextView txtDiceToRoll = (TextView) view.findViewById(R.id.txtDiceToRoll);
        Button btnIncreaseDice = (Button) view.findViewById(R.id.btnIncreaseDice);
        Button btnDecreaseDice = (Button) view.findViewById(R.id.btnDecreaseDice);
        final TextView txtExtraRolls = (TextView) view.findViewById(R.id.txtExtraRolls);
        Button btnIncreaseRolls = (Button) view.findViewById(R.id.btnIncreaseRolls);
        Button btnDecreaseRolls = (Button) view.findViewById(R.id.btnDecreaseRolls);

        final LinearLayout panelCustomizePointBuy = (LinearLayout) view
            .findViewById(R.id.panelCustomizePointBuy);
        final TextView txtPoints = (TextView) view.findViewById(R.id.txtPoints);
        Button btnIncreasePoints = (Button) view.findViewById(R.id.btnIncreasePoints);
        Button btnDecreasePoints = (Button) view.findViewById(R.id.btnDecreasePoints);

        // Conceal all toggleable panels
        panelCustomizeRolls.setVisibility(View.GONE);
        panelCustomizePointBuy.setVisibility(View.GONE);

        // Set default values for text views
        txtDiceToRoll.setText(String.valueOf(Constants.DEFAULT_DICE));
        txtExtraRolls.setText(String.valueOf(Constants.DEFAULT_EXTRA_ROLLS));
        txtPoints.setText(String.valueOf(Constants.DEFAULT_POINT_POOL));

        // Set listeners for everything that needs it
        chkRoll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chkRoll.isChecked()) {
                    uncheckAllOthers(chkRoll);
                    panelCustomizeRolls.setVisibility(View.VISIBLE);
                } else {
                    panelCustomizeRolls.setVisibility(View.GONE);
                }
            }
        });

        chkPointBuy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chkPointBuy.isChecked()) {
                    uncheckAllOthers(chkPointBuy);
                    panelCustomizePointBuy.setVisibility(View.VISIBLE);
                } else {
                    panelCustomizePointBuy.setVisibility(View.GONE);
                }
            }
        });

        chkManual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chkManual.isChecked()) {
                    uncheckAllOthers(chkManual);
                }
            }
        });

        chkUseStdScores.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chkUseStdScores.isChecked()) {
                    uncheckAllOthers(chkUseStdScores);
                }
            }
        });

        btnIncreaseDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dice++;
                txtDiceToRoll.setText(String.valueOf(dice));
            }
        });

        btnDecreaseDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dice--;
                txtDiceToRoll.setText(String.valueOf(dice));
            }
        });

        btnIncreaseRolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extraRolls++;
                txtExtraRolls.setText(String.valueOf(extraRolls));
            }
        });

        btnDecreaseRolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extraRolls--;
                txtExtraRolls.setText(String.valueOf(extraRolls));
            }
        });

        btnIncreasePoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointPool++;
                txtPoints.setText(String.valueOf(pointPool));
            }
        });

        btnDecreasePoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointPool--;
                txtPoints.setText(String.valueOf(pointPool));
            }
        });

        builder
            .setView(view)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    SharedPreferences settings = getSharedPrefs();

                    SharedPreferences.Editor editor = settings.edit();

                    editor.putBoolean(getActivity().getString(R.string.pref_method_manual),
                        chkManual.isChecked());
                    editor.putBoolean(getActivity().getString(R.string.pref_method_roll),
                        chkRoll.isChecked());
                    editor.putBoolean(getActivity().getString(R.string.pref_method_pointbuy),
                        chkPointBuy.isChecked());
                    editor.putBoolean(getActivity().getString(R.string.pref_method_stdscores),
                        chkUseStdScores.isChecked());

                    editor.putBoolean(
                        getActivity().getString(R.string.pref_method_roll_allowstatedit),
                        chkRoll.isChecked() ? chkAllowEdition.isChecked() : chkPointBuy.isChecked());

                    editor.putInt(getActivity().getString(R.string.pref_value_dice), dice);
                    editor.putInt(getActivity().getString(R.string.pref_value_extrarolls),
                        extraRolls);
                    editor
                        .putInt(getActivity().getString(R.string.pref_value_pointpool), pointPool);

                    editor.apply();

                    listener.onInputClickOk("");

                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    private void uncheckAllOthers(CheckBox checkBox) {
        if (checkBox != chkManual) {
            chkManual.setChecked(false);
        }
        if (checkBox != chkPointBuy) {
            chkPointBuy.setChecked(false);
        }
        if (checkBox != chkRoll) {
            chkRoll.setChecked(false);
        }
        if (checkBox != chkUseStdScores) {
            chkUseStdScores.setChecked(false);
        }
    }

    private SharedPreferences getSharedPrefs() {
        return getActivity().getSharedPreferences(
            getString(R.string.tag_statalloc_settings), Context.MODE_PRIVATE);
    }

}
