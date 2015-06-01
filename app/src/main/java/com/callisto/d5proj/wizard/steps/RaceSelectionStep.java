package com.callisto.d5proj.wizard.steps;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.callisto.d5proj.db.tables.FeaturesDBAdapter;
import com.callisto.d5proj.db.tables.RacesDBAdapter;
import com.callisto.d5proj.enums.BaseStatistic;
import com.callisto.d5proj.pojos.Feature;
import com.callisto.d5proj.pojos.Race;
import com.google.gson.Gson;

import org.codepond.wizardroid.WizardStep;

import java.util.Iterator;
import java.util.List;

/**
 * Race selection fragment for wizard.
 * Created by emiliano.desantis on 11/05/2015.
 */
public class RaceSelectionStep extends WizardStep {

    Race race;

    public RaceSelectionStep() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wizard_select_race, container, false);

        FeaturesDBAdapter featuresDBAdapter = new FeaturesDBAdapter(this.getActivity());

        RacesDBAdapter racesDBAdapter = new RacesDBAdapter(this.getActivity());

        features = featuresDBAdapter.getAllFeatures();

        racesDBAdapter.setFeatureList(features);

        races = racesDBAdapter.getAllRaces();

        findComponents(rootView);

        return rootView;
    }

    private void findComponents(View rootView) {
        spinnerSelectRace = (Spinner) rootView.findViewById(R.id.spinnerSelectRace);

        containerRaceStats = (LinearLayout) rootView.findViewById(R.id.containerRaceStats);

        spinnerSelectRace.setAdapter(new RaceSelectorAdapter(getActivity().getBaseContext(), races));

        spinnerSelectRace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                containerRaceStats.removeAllViewsInLayout();

                race = (Race) spinnerSelectRace.getSelectedItem();

                storeInPrefs(race);

                populateRaceModifiers(race);
                populateRacialAbilities(race);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    private void storeInPrefs(Race r) {
        SharedPreferences.Editor editor = getCharSharedPrefs().edit();
        Gson gson = new Gson();
        String json = gson.toJson(r);
        editor.putString(getResources().getString(R.string.C_CLASS_RACE), json);
        editor.apply();
    }

    private void populateRaceModifiers(Race race) {
        Iterator<Pair<BaseStatistic, Integer>> I = race.getStatModifiers().iterator();

        while (I.hasNext()) {
            Pair<BaseStatistic, Integer> statMod = I.next();

            TextView textView = new TextView(getActivity().getBaseContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setText("+" + statMod.second.toString()
                + " " + statMod.first.toString());
            textView.setTextColor(Color.BLACK);

            containerRaceStats.addView(textView);
        }
    }

    private void populateRacialAbilities(Race race) {
        Iterator<Feature> I = race.getRacialFeatures().iterator();

        while (I.hasNext()) {
            Feature f = I.next();

            TextView textView = new TextView(getActivity().getBaseContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setTextColor(Color.BLACK);
            textView.setText(f.getName());

            containerRaceStats.addView(textView);
        }
    }

    private SharedPreferences getCharSharedPrefs() {
        Context context = getActivity();
        return context.getSharedPreferences(
            getString(R.string.character_stats), Context.MODE_PRIVATE);
    }

    private Spinner spinnerSelectRace;

    private LinearLayout containerRaceStats;

    List<Race> races;
    List<Feature> features;
}
