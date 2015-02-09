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
import com.callisto.d5proj.pojos.Level;
import com.callisto.d5proj.widgets.CharSheetStatBox;
import com.callisto.d5proj.widgets.InputDialog;

import java.util.ArrayList;
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

        findComponents(rootView);

        charStr.setAttributeValue(STR);
        charDex.setAttributeValue(DEX);
        charCon.setAttributeValue(CON);
        charInt.setAttributeValue(INT);
        charWis.setAttributeValue(WIS);
        charCha.setAttributeValue(CHA);

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
                    xp = Integer.parseInt(s.toString());

                    Iterator<Level> I = ((CharacterCreationActivity) getActivity()).getExperienceTable().iterator();

                    Level level = I.next();

                    while (level.getExperience() < xp) {
                        txtLevelNumber.setText(String.valueOf(level.getNumber()));
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
            STR = value;
            break;
        }
        case DEX: {
            DEX = value;
            break;
        }
        case CON: {
            CON = value;
            break;
        }
        case INT: {
            INT = value;
            break;
        }
        case WIS: {
            WIS = value;
            break;
        }
        case CHA: {
            CHA = value;
            break;
        }
        }
    }

    @Override
    public void onInputClickOk(String text) {
        int addedXP = Integer.parseInt(text);

        txtXPNumber.setText(String.valueOf(xp = xp + addedXP));
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

    private int STR;
    private int DEX;
    private int INT;
    private int CON;
    private int WIS;
    private int CHA;
    private int xp;
    private int hitPoints;
    private int armorClass;
    private ArrayList<Level> characterLevels;

    private TextView txtXPNumber;
    private TextView txtLevelNumber;
    private TextView txtProfBonus;

    private DerivedStatsFragment localInstance;
}
