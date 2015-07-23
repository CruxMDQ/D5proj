package com.callisto.d5proj.fragments.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.callisto.d5proj.R;

/**
 * Created by Crux on 21/07/2015.
 */
public class StatStepSettingsDialogFragment extends android.support.v4.app.DialogFragment {
    static private int DEFAULT_DICE = 4;
    static private int DEFAULT_EXTRA_ROLLS = 0;
    static private int DEFAULT_POINT_POOL = 27;

//    public static StatStepSettingsDialogFragment newInstance(AfterChoosingOptionsListener listener)

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Define and retrieve widgets
        View view = inflater.inflate(R.layout.dialog_statalloc_settings, null);

        CheckBox chkUseStdScores = (CheckBox) view.findViewById(R.id.chkUseStdScores);
        CheckBox chkRoll = (CheckBox) view.findViewById(R.id.chkRoll);
        CheckBox chkPointBuy = (CheckBox) view.findViewById(R.id.chkPointBuy);
        CheckBox chkManual = (CheckBox) view.findViewById(R.id.chkManual);

        final LinearLayout panelCustomizeRolls = (LinearLayout) view.findViewById(R.id.panelCustomizeRolls);
        TextView txtDice = (TextView) view.findViewById(R.id.txtDice);
        Button btnIncreaseDice = (Button) view.findViewById(R.id.btnIncreaseDice);
        Button btnDecreaseDice = (Button) view.findViewById(R.id.btnDecreaseDice);
        TextView txtExtraRolls = (TextView) view.findViewById(R.id.txtExtraRolls);
        Button btnIncreaseRolls = (Button) view.findViewById(R.id.btnIncreaseRolls);
        Button btnDecreaseRolls = (Button) view.findViewById(R.id.btnDecreaseRolls);

        final LinearLayout panelCustomizePointBuy = (LinearLayout) view.findViewById(R.id.panelCustomizePointBuy);
        TextView txtPoints = (TextView) view.findViewById(R.id.txtPoints);
        Button btnIncreasePoints = (Button) view.findViewById(R.id.btnIncreasePoints);
        Button btnDecreasePoints = (Button) view.findViewById(R.id.btnDecreasePoints);

        // Conceal all toggleable panels
        panelCustomizeRolls.setVisibility(View.GONE);
        panelCustomizePointBuy.setVisibility(View.GONE);

        // Set default values for text views
        txtDice.setText(String.valueOf(DEFAULT_DICE));
        txtExtraRolls.setText(String.valueOf(DEFAULT_EXTRA_ROLLS));
        txtPoints.setText(String.valueOf(DEFAULT_POINT_POOL));

        // Set listeners for everything that needs it
        chkRoll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    panelCustomizeRolls.setVisibility(View.VISIBLE);
                } else {
                    panelCustomizeRolls.setVisibility(View.GONE);
                }
            }
        });

        chkPointBuy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    panelCustomizePointBuy.setVisibility(View.VISIBLE);
                } else {
                    panelCustomizePointBuy.setVisibility(View.GONE);
                }
            }
        });

//        Spinner spin;
//        spin = (Spinner)view.findViewById(R.id.spinner1);
//
//        List<String> list = new ArrayList<String>();
//        list.add("Material 1");
//        list.add("Material 2");
//        list.add("Material 3");
//        list.add("Material 4");
//        list.add("Material 5");
//        list.add("Material 6");
//
//        //Second List
//        List<String> list2 = new ArrayList<String>();
//        list2.add("Mat 7");
//        list2.add("Mat 8");
//        list2.add("Mat 9");
//
//        //Combined List
//        List<String> listCombine = new ArrayList<String>();
//        listCombine.addAll(list);
//        listCombine.addAll(list2);
//
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_spinner_item, listCombine);
//
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spin.setAdapter(dataAdapter);

        builder
                .setTitle("Title")
                .setView(inflater.inflate(R.layout.dialog_statalloc_settings, null))
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO Set logic for storing preferences here... or notify a listener?
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
}
