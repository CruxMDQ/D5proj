package com.callisto.d5proj.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.callisto.d5proj.R;
import com.callisto.d5proj.activities.CharacterCreationActivity;
import com.callisto.d5proj.adapters.ClassSelectorAdapter;

/**
 * Created by emiliano.desantis on 10/02/2015.
 */
public class PickChoicesDFragment extends android.support.v4.app.DialogFragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static PickChoicesDFragment newInstance(int sectionNumber) {
        PickChoicesDFragment fragment = new PickChoicesDFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

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
        LayoutInflater li = LayoutInflater.from(getActivity());

        View promptsView = li.inflate(R.layout.dialog_select_class, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setView(promptsView);

        // set dialog message

        alertDialogBuilder.setTitle(getString(R.string.select_char_class));
        alertDialogBuilder.setIcon(R.drawable.ic_launcher);
        // create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();

        final Spinner mSpinner = (Spinner) promptsView
            .findViewById(R.id.spinnerSelectClass);

        mSpinner.setAdapter(new ClassSelectorAdapter(getActivity(),
            ((CharacterCreationActivity) getActivity()).getCharacterClasses()));

        final Button mButton = (Button) promptsView
            .findViewById(R.id.buttonOK);

        // reference UI elements from my_dialog_layout in similar fashion

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // show it
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);
    }

}
