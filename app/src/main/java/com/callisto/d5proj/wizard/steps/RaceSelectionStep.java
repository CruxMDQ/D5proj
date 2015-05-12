package com.callisto.d5proj.wizard.steps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.callisto.d5proj.R;
import com.callisto.d5proj.adapters.RaceSelectorAdapter;
import com.callisto.d5proj.db.tables.RacesDBAdapter;
import com.callisto.d5proj.enums.BaseStatistic;
import com.callisto.d5proj.pojos.Race;

import org.codepond.wizardroid.WizardStep;

import java.util.Iterator;
import java.util.List;

/**
 * Race selection fragment for wizard.
 * Created by emiliano.desantis on 11/05/2015.
 */
public class RaceSelectionStep extends WizardStep {

    public RaceSelectionStep() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_wizard_select_race, container, false);

        racesDBAdapter = new RacesDBAdapter(this.getActivity());

        findComponents(rootView);

        return rootView;
    }

    private void findComponents(View rootView) {
        spinnerSelectRace = (Spinner) rootView.findViewById(R.id.spinnerSelectRace);

        containerRaceStats = (LinearLayout) rootView.findViewById(R.id.containerRaceStats);

        races = racesDBAdapter.getAllRaces();

        spinnerSelectRace.setAdapter(new RaceSelectorAdapter(getActivity().getBaseContext(), races));

        spinnerSelectRace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                containerRaceStats.removeAllViewsInLayout();

                Race race = (Race) spinnerSelectRace.getSelectedItem();

                populateRaceModifiers(race);

                if (race.getParent() != null) {
                    populateRaceModifiers(race.getParent());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) { }
        });
    }

    private void populateRaceModifiers(Race race) {
        Iterator I = race.getStatModifiers().iterator();

        while (I.hasNext()) {
            Pair<BaseStatistic, Integer> statMod = (Pair<BaseStatistic, Integer>) I.next();

            TextView textView = new TextView(getActivity().getBaseContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setText("+" + statMod.second.toString()
                + " " + statMod.first.toString());
            textView.setTextColor(Color.BLACK);

            containerRaceStats.addView(textView);
        }
    }

    private View rootView;

    private Spinner spinnerSelectRace;

    private LinearLayout containerRaceStats;

    private RacesDBAdapter racesDBAdapter;

    List<Race> races;
}
