package com.callisto.d5proj.wizard.steps;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.callisto.d5proj.Constants;
import com.callisto.d5proj.R;
import com.callisto.d5proj.adapters.RaceSelectorAdapter;
import com.callisto.d5proj.adapters.RaceStepRVAdapter;
import com.callisto.d5proj.db.tables.RacesTableHelper;
import com.callisto.d5proj.db.tables.SpellsTableHelper;
import com.callisto.d5proj.enums.BaseStatistic;
import com.callisto.d5proj.fragments.dialogs.PickFeatureDialogFragment;
import com.callisto.d5proj.fragments.dialogs.PickSpellsDialogFragment;
import com.callisto.d5proj.interfaces.AfterChoosingOptionsListener;
import com.callisto.d5proj.interfaces.OnChoosingOptionsListener;
import com.callisto.d5proj.pojos.Feature;
import com.callisto.d5proj.pojos.GameActor;
import com.callisto.d5proj.pojos.Race;
import com.callisto.d5proj.pojos.Spell;
import com.google.gson.Gson;

import org.codepond.wizardroid.WizardStep;

import java.util.ArrayList;

/**
 * Race selection fragment for wizard.
 * Created by emiliano.desantis on 11/05/2015.
 */
public class RaceSelectionStep extends WizardStep implements OnChoosingOptionsListener, AfterChoosingOptionsListener {

    public RaceSelectionStep() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wizard_select_race, container, false);

        rvFeatures = (RecyclerView) rootView.findViewById(R.id.rvFeatures);
        rvFeatures.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        resetRaces();

        findComponents(rootView);

        return rootView;
    }

    private void resetRaces() {
        RacesTableHelper racesDBAdapter = new RacesTableHelper(this.getActivity());
        spellsTableHelper = new SpellsTableHelper(this.getActivity());

        races = racesDBAdapter.getAllRaces();

        if (spinnerSelectRace != null) {
            spinnerSelectRace.setAdapter(new RaceSelectorAdapter(getActivity().getBaseContext(), races));
        }

        if (rvFeatures != null) {
            if (rvFeatures.getAdapter() != null) {
                rvFeatures.getAdapter().notifyDataSetChanged();
            }
        }
    }

//    @Override
//    public void afterChoosingOptions(Feature feature, ArrayList<Feature> choices) {
//        // TODO: Implement 'Character' class and copy features from selected race. How to store it? A SharedPreference? Use DB?
//        character.getFeatures().remove(feature);
//        rvFeatures.getAdapter().notifyDataSetChanged();
//        character.getFeatures().addAll(choices);
//        rvFeatures.getAdapter().notifyDataSetChanged();
//        pickedFeatures = true;
//    }

    private void findComponents(View rootView) {
        spinnerSelectRace = (Spinner) rootView.findViewById(R.id.spinnerSelectRace);

        containerRaceStats = (LinearLayout) rootView.findViewById(R.id.containerRaceStats);

        spinnerSelectRace.setAdapter(new RaceSelectorAdapter(getActivity().getBaseContext(), races));

        spinnerSelectRace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (pickedFeatures) {
                    resetRaces();
                    character.resetSpells();
                    spinnerSelectRace.setSelection(position);
                    pickedFeatures = false;
                }

                containerRaceStats.removeAllViewsInLayout();

                race = (Race) spinnerSelectRace.getSelectedItem();

                character.setRace(race);
                character.setFeatures(race.getRacialFeatures());

                storeInPrefs(race);

                populateRaceModifiers(race);
                populateRacialAbilities(character.getFeatures());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void storeActorInPrefs() {
        SharedPreferences.Editor editor = getCharSharedPrefs().edit();
        Gson gson = new Gson();
        String json = gson.toJson(character);
        editor.putString(getResources().getString(R.string.C_CLASS_GAME_ACTOR), json);
        editor.apply();
    }

    private void storeInPrefs(Race race) {
        SharedPreferences.Editor editor = getCharSharedPrefs().edit();
        Gson gson = new Gson();
        String json = gson.toJson(race);
        editor.putString(getResources().getString(R.string.C_CLASS_RACE), json);
        editor.apply();
    }

    private void populateRaceModifiers(Race race) {
        for (Pair<BaseStatistic, Integer> statMod : race.getStatModifiers()) {
            TextView textView = new TextView(getActivity().getBaseContext());
            textView
                .setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setText("+" + statMod.second.toString()
                + " " + statMod.first.toString());
            textView.setTextColor(Color.BLACK);

            containerRaceStats.addView(textView);
        }

        if (race.getStatModifiers().size() == 0) {
            TextView textView = new TextView(getActivity().getBaseContext());
            textView
                .setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setText(getString(R.string.stat_mods_none));
            textView.setTextColor(Color.BLACK);

            containerRaceStats.addView(textView);
        }
    }

    private void populateRacialAbilities(ArrayList<Feature> features) {
        rvFeatures.setAdapter(new RaceStepRVAdapter(getActivity(), features, this));
    }

    private SharedPreferences getCharSharedPrefs() {
        Context context = getActivity();
        return context.getSharedPreferences(
            getString(R.string.character_stats), Context.MODE_PRIVATE);
    }

    @Override
    public void onInputClick(Feature feature) {
        switch (feature.getId()) {
        case Constants.FEATURE_CODE_CANTRIP: {
            PickSpellsDialogFragment pickSpells = PickSpellsDialogFragment
                .newInstance(feature, spellsTableHelper.filterSpellsPerLevel(Constants.SPELL_LEVEL_0), Constants.SPELL_LEVEL_0, this);
            pickSpells.setModal(true);
            pickSpells.show(getActivity().getSupportFragmentManager(), "PickSpells");
            break;
        }
        default: {
            PickFeatureDialogFragment pickChoices = PickFeatureDialogFragment
                .newInstance(feature, this);
            pickChoices.setModal(true);
            pickChoices.show(getActivity().getSupportFragmentManager(), "PickChoices");
            break;
        }
        }
    }

    @Override
    public void afterChoosingOptions(Object object, ArrayList choices) {
        if (object instanceof Feature) {
            Feature feature = (Feature) object;
            for (Object o : choices) {
                if (o instanceof Feature) {
                    character.getFeatures().remove(object);
                    character.getFeatures().add((Feature) o);
                    rvFeatures.getAdapter().notifyDataSetChanged();
                    pickedFeatures = true;
                } else if (o instanceof Spell) {
                    if (!character.knowsSpell((Spell) o)) {
                        character.getKnownSpells().add((Spell) o);
                    }
                    feature.setChoices(0);
                    feature.setName(feature.getName() + " - " + ((Spell) o).getName());
                    character.getFeatures().remove(object);
                    character.getFeatures().add(feature);
                    rvFeatures.getAdapter().notifyDataSetChanged();
                    pickedFeatures = true;
                }
            }
        }
    }

    private boolean pickedFeatures = false;

    private Spinner spinnerSelectRace;

    private LinearLayout containerRaceStats;

    private ArrayList<Race> races;

    private Race race;

    private RecyclerView rvFeatures;

    private GameActor character = new GameActor();

    private SpellsTableHelper spellsTableHelper;
}
