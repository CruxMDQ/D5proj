package com.callisto.d5proj.fragments.dialogs;

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
public class SelectClassDialogFragment extends android.support.v4.app.DialogFragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static SelectClassDialogFragment newInstance(int sectionNumber) {
        SelectClassDialogFragment fragment = new SelectClassDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public SelectClassDialogFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_select_class, container, false);

        findComponents(rootView);

        showClassSelectorDialog();

        return rootView;
    }

    private void findComponents(View rootView) {

    }

    private void showClassSelectorDialog() {
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
