package com.callisto.d5proj.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.callisto.d5proj.R;
import com.callisto.d5proj.activities.CharacterCreationActivity;
import com.callisto.d5proj.enums.BaseStatistic;
import com.callisto.d5proj.widgets.CharSheetStatBox;

/**
 * Created by emiliano.desantis on 06/02/2015.
 */
public class DerivedStatsFragment extends Fragment {

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
    private int XP;
    private int level;

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
        charStr = (CharSheetStatBox) rootView.findViewById(R.id.charStr);
        charDex = (CharSheetStatBox) rootView.findViewById(R.id.charDex);
        charCon = (CharSheetStatBox) rootView.findViewById(R.id.charCon);
        charInt = (CharSheetStatBox) rootView.findViewById(R.id.charInt);
        charWis = (CharSheetStatBox) rootView.findViewById(R.id.charWis);
        charCha = (CharSheetStatBox) rootView.findViewById(R.id.charCha);
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

}
