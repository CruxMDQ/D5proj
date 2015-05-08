package com.callisto.d5proj.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.callisto.d5proj.R;
import com.callisto.d5proj.activities.CharacterCreationActivity;
import com.callisto.d5proj.enums.BaseStatistic;
import com.callisto.d5proj.interfaces.OnInputClickListener;
import com.callisto.d5proj.pojos.GameActor;
import com.callisto.d5proj.pojos.Level;
import com.callisto.d5proj.widgets.CharSheetStatBox;
import com.callisto.d5proj.widgets.InputDialog;

import java.util.Iterator;

/**
 * Created by emiliano.desantis on 06/02/2015.
 */
public class DerivedStatsFragment extends Fragment implements OnInputClickListener {

    public static DerivedStatsFragment newInstance(int sectionNumber) {
        DerivedStatsFragment fragment = new DerivedStatsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public DerivedStatsFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((CharacterCreationActivity) activity).onSectionAttached(
            getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_derived_stats, container, false);

        actor = new GameActor();

        findComponents(rootView);

        charStr.setAttributeValue(actor.getSTR());
        charDex.setAttributeValue(actor.getDEX());
        charCon.setAttributeValue(actor.getCON());
        charInt.setAttributeValue(actor.getINT());
        charWis.setAttributeValue(actor.getWIS());
        charCha.setAttributeValue(actor.getCHA());

        return rootView;
    }

    private void findComponents(View rootView) {
        localInstance = this;

        charStr = (CharSheetStatBox) rootView.findViewById(R.id.charStr);
        charDex = (CharSheetStatBox) rootView.findViewById(R.id.charDex);
        charCon = (CharSheetStatBox) rootView.findViewById(R.id.charCon);
        charInt = (CharSheetStatBox) rootView.findViewById(R.id.charInt);
        charWis = (CharSheetStatBox) rootView.findViewById(R.id.charWis);
        charCha = (CharSheetStatBox) rootView.findViewById(R.id.charCha);

        txtXPNumber = (TextView) rootView.findViewById(R.id.txtXPNumber);
        txtXPNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputDialog inputDialog = new InputDialog(getActivity(), "Add experience points",
                    "Enter amount of XP earned", localInstance);

                inputDialog.show();
            }
        });
        txtXPNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    actor.setXp(Integer.parseInt(s.toString()));

                    Iterator<Level> I = ((CharacterCreationActivity) getActivity()).getExperienceTable().iterator();

                    Level level = I.next();

                    while (level.getExperience() < actor.getXp()) {
                        txtLevelNumber.setText(String.valueOf(level.getNumber()));
                        txtProfBonus.setText(String.valueOf(level.getProficiencyBonus()));
                        level = I.next();
                    }
                }
            }
        });

        txtLevelNumber = (TextView) rootView.findViewById(R.id.txtLevel);
        txtProfBonus = (TextView) rootView.findViewById(R.id.txtProfBonus);
    }

    public void setStatFromBuilder(BaseStatistic stat, int value) {
        switch (stat) {
        case STR: {
            actor.setSTR(value);
            break;
        }
        case DEX: {
            actor.setDEX(value);
            break;
        }
        case CON: {
            actor.setCON(value);
            break;
        }
        case INT: {
            actor.setINT(value);
            break;
        }
        case WIS: {
            actor.setWIS(value);
            break;
        }
        case CHA: {
            actor.setCHA(value);
            break;
        }
        }
    }

    @Override
    public void onInputClickOk(String text) {
        int currentXp = actor.getXp();
        int addedXP = Integer.parseInt(text);

        currentXp = currentXp + addedXP;
        actor.setXp(currentXp);

        txtXPNumber.setText(String.valueOf(actor.getXp()));
    }

    @Override
    public void onInputClickCancel(String text) { }

    private static final String ARG_SECTION_NUMBER = "section_number";

    private View rootView;

    private CharSheetStatBox charStr;
    private CharSheetStatBox charDex;
    private CharSheetStatBox charCon;
    private CharSheetStatBox charInt;
    private CharSheetStatBox charWis;
    private CharSheetStatBox charCha;

    private GameActor actor;

    private TextView txtXPNumber;
    private TextView txtLevelNumber;
    private TextView txtProfBonus;

    private DerivedStatsFragment localInstance;
}
