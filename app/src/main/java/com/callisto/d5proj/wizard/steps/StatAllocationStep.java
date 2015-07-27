package com.callisto.d5proj.wizard.steps;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.callisto.d5proj.Constants;
import com.callisto.d5proj.R;
import com.callisto.d5proj.enums.BaseStatistic;
import com.callisto.d5proj.fragments.dialogs.StatStepSettingsDialogFragment;
import com.callisto.d5proj.interfaces.OnInputClickListener;
import com.callisto.d5proj.pojos.Race;
import com.callisto.d5proj.tools.Roller;
import com.callisto.d5proj.widgets.EditableStatBox;
import com.google.gson.Gson;

import org.codepond.wizardroid.WizardStep;

import java.util.Arrays;
import java.util.Collections;

public class StatAllocationStep extends WizardStep implements OnInputClickListener {

    public StatAllocationStep() { super(); }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.stat_alloc, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings: {
                StatStepSettingsDialogFragment settingsDialogFragment = StatStepSettingsDialogFragment.newInstance(this);
                settingsDialogFragment.show(getActivity().getFragmentManager(),
                    getStringResource(R.string.tag_statalloc_settings));
                break;
            }
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentMode = getStringResource(R.string.pref_method_roll);

        rootView = inflater.inflate(R.layout.fragment_wizard_stat_edition, container, false);

        findComponents(rootView);

        if (!hasPreviousRolls()) {
            generateRolls();
        }

        setRolls();

        return rootView;
    }

    private void setRaceModifiers() {
        editableStatBoxStr.setAttributeBonus(0);
        editableStatBoxDex.setAttributeBonus(0);
        editableStatBoxCon.setAttributeBonus(0);
        editableStatBoxInt.setAttributeBonus(0);
        editableStatBoxWis.setAttributeBonus(0);
        editableStatBoxCha.setAttributeBonus(0);

        if (race != null) {
            for (Pair<BaseStatistic, Integer> statMod : race.getStatModifiers()) {
                switch(statMod.first) {
                case STR:
                    editableStatBoxStr.setAttributeBonus(statMod.second);
                    break;
                case DEX:
                    editableStatBoxDex.setAttributeBonus(statMod.second);
                    break;
                case CON:
                    editableStatBoxCon.setAttributeBonus(statMod.second);
                    break;
                case INT:
                    editableStatBoxInt.setAttributeBonus(statMod.second);
                    break;
                case WIS:
                    editableStatBoxWis.setAttributeBonus(statMod.second);
                    break;
                case CHA:
                    editableStatBoxCha.setAttributeBonus(statMod.second);
                    break;
                }
            }
        }
    }

    private SharedPreferences getCharSharedPrefs() {
        Context context = getActivity();
        return context.getSharedPreferences(
            getString(R.string.tag_character_stats), Context.MODE_PRIVATE);
    }

    private void findComponents(View rootView) {
        btnRollNewValues = (Button) rootView.findViewById(R.id.btnRollNewValues);
        btnRollNewValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reRoll();
            }
        });

        txtRandomAttribute1 = (TextView) rootView.findViewById(R.id.txtRandomAttribute1);
        txtRandomAttribute2 = (TextView) rootView.findViewById(R.id.txtRandomAttribute2);
        txtRandomAttribute3 = (TextView) rootView.findViewById(R.id.txtRandomAttribute3);
        txtRandomAttribute4 = (TextView) rootView.findViewById(R.id.txtRandomAttribute4);
        txtRandomAttribute5 = (TextView) rootView.findViewById(R.id.txtRandomAttribute5);
        txtRandomAttribute6 = (TextView) rootView.findViewById(R.id.txtRandomAttribute6);

        txtPointPool = (TextView) rootView.findViewById(R.id.txtPointPool);
        txtTotalRolls = (TextView) rootView.findViewById(R.id.txtTotalRolls);

        editableStatBoxStr = (EditableStatBox) rootView.findViewById(R.id.statBoxStr);
        prepareStatBox(editableStatBoxStr);

        editableStatBoxDex = (EditableStatBox) rootView.findViewById(R.id.statBoxDex);
        prepareStatBox(editableStatBoxDex);

        editableStatBoxCon = (EditableStatBox) rootView.findViewById(R.id.statBoxCon);
        prepareStatBox(editableStatBoxCon);

        editableStatBoxInt = (EditableStatBox) rootView.findViewById(R.id.statBoxInt);
        prepareStatBox(editableStatBoxInt);

        editableStatBoxWis = (EditableStatBox) rootView.findViewById(R.id.statBoxWis);
        prepareStatBox(editableStatBoxWis);

        editableStatBoxCha = (EditableStatBox) rootView.findViewById(R.id.statBoxCha);
        prepareStatBox(editableStatBoxCha);

        btnResetRolls = (Button) rootView.findViewById(R.id.btnResetRolls);
        btnResetRolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    // TODO (low): find a way to refactor this into the EditableStatBox class proper
    private void prepareStatBox(final EditableStatBox editableStatBox) {
        editableStatBox.getTxtAttributeRoll().setOnDragListener(new ChoiceDragListener());

        // Source: http://stackoverflow.com/questions/20824634/
        editableStatBox.getTxtAttributeRoll().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                int attributeValue = Integer.parseInt(s.toString());
                editableStatBox.setAttribute(attributeValue);
//                onStatChangeListener.onStatChange(baseStatistic, attributeValue);
            }
        });

        editableStatBox.getBtnDecreaseValue().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseStat(editableStatBox);
            }
        });

        editableStatBox.getBtnIncreaseValue().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseStat(editableStatBox);
            }
        });

        editableStatBox.disableButtons();
    }

    private boolean hasPreviousRolls() {
        return !getCharSharedPrefs().getAll().isEmpty();
    }

    private void generateRolls() {
        SharedPreferences settings = getActivity().getSharedPreferences(
            getString(R.string.tag_statalloc_settings), Context.MODE_PRIVATE);

        if (settings.getBoolean(getStringResource(R.string.pref_method_roll), true)) {
            setMethodRoll();
        } else if (settings.getBoolean(getStringResource(R.string.pref_method_stdscores), false)) {
            setMethodStdScores();
        } else if (settings.getBoolean(getStringResource(R.string.pref_method_pointbuy), false)) {
            setMethodPointBuy();
        }
    }

    private String getStringResource(int pref_method_roll) {
        return getActivity().getResources().getString(pref_method_roll);
    }

    private void setMethodPointBuy() {
        currentMode = getStringResource(R.string.pref_method_pointbuy);

        SharedPreferences.Editor editor = getCharSharedPrefs().edit();

        SharedPreferences settings = getActivity().getSharedPreferences(
            getString(R.string.tag_statalloc_settings), Context.MODE_PRIVATE);

        disableRollsPanel();
        disableRolling();

        setPointPool(settings.getInt("pointPool", Constants.DEFAULT_POINT_POOL));

        editableStatBoxStr.setAttribute(8);
        editableStatBoxDex.setAttribute(8);
        editableStatBoxCon.setAttribute(8);
        editableStatBoxInt.setAttribute(8);
        editableStatBoxWis.setAttribute(8);
        editableStatBoxCha.setAttribute(8);

        editableStatBoxStr.setMinimumValue(8);
        editableStatBoxDex.setMinimumValue(8);
        editableStatBoxCon.setMinimumValue(8);
        editableStatBoxInt.setMinimumValue(8);
        editableStatBoxWis.setMinimumValue(8);
        editableStatBoxCha.setMinimumValue(8);

        editableStatBoxStr.getTxtAttributeRoll().setText(String.valueOf(editableStatBoxStr.getAttributeRoll()));
        editableStatBoxDex.getTxtAttributeRoll().setText(String.valueOf(editableStatBoxDex.getAttributeRoll()));
        editableStatBoxCon.getTxtAttributeRoll().setText(String.valueOf(editableStatBoxCon.getAttributeRoll()));
        editableStatBoxInt.getTxtAttributeRoll().setText(String.valueOf(editableStatBoxInt.getAttributeRoll()));
        editableStatBoxWis.getTxtAttributeRoll().setText(String.valueOf(editableStatBoxWis.getAttributeRoll()));
        editableStatBoxCha.getTxtAttributeRoll().setText(String.valueOf(editableStatBoxCha.getAttributeRoll()));

        editableStatBoxStr.enableButtons();
        editableStatBoxDex.enableButtons();
        editableStatBoxCon.enableButtons();
        editableStatBoxInt.enableButtons();
        editableStatBoxWis.enableButtons();
        editableStatBoxCha.enableButtons();

        editor.apply();
    }

    private void setMethodStdScores() {
        currentMode = getStringResource(R.string.pref_method_stdscores);

        SharedPreferences.Editor editor = getCharSharedPrefs().edit();

        enableRollsPanel();

        editor.putString("rand1", String.valueOf(15));
        editor.putString("rand2", String.valueOf(14));
        editor.putString("rand3", String.valueOf(13));
        editor.putString("rand4", String.valueOf(12));
        editor.putString("rand5", String.valueOf(10));
        editor.putString("rand6", String.valueOf(8));

        editor.apply();
    }

    private void setMethodRoll() {
        currentMode = getStringResource(R.string.pref_method_roll);

        SharedPreferences.Editor editor = getCharSharedPrefs().edit();

        SharedPreferences settings = getActivity().getSharedPreferences(
            getString(R.string.tag_statalloc_settings), Context.MODE_PRIVATE);

        Integer[] rolls;
        enableRollsPanel();

        dice = settings.getInt(getStringResource(R.string.pref_value_dice), Constants.DEFAULT_DICE);
        extraRolls = settings.getInt(getStringResource(R.string.pref_value_extrarolls), Constants.DEFAULT_EXTRA_ROLLS);

        rolls = new Integer[6 + extraRolls];

        for (int i = 0; i < 6 + extraRolls; i++) {
            rolls[i] = Roller.rollDice(dice, 6, 1);
        }

        Arrays.sort(rolls, Collections.reverseOrder());

        editor.putString("rand1", String.valueOf(rolls[0]));
        editor.putString("rand2", String.valueOf(rolls[1]));
        editor.putString("rand3", String.valueOf(rolls[2]));
        editor.putString("rand4", String.valueOf(rolls[3]));
        editor.putString("rand5", String.valueOf(rolls[4]));
        editor.putString("rand6", String.valueOf(rolls[5]));

        increaseRollCount();

        editor.putString("totalRolls", String.valueOf(totalRolls));

        if (settings.getBoolean(getStringResource(R.string.pref_method_roll_allowstatedit), false)) {
            editableStatBoxStr.enableButtons();
            editableStatBoxDex.enableButtons();
            editableStatBoxCon.enableButtons();
            editableStatBoxInt.enableButtons();
            editableStatBoxWis.enableButtons();
            editableStatBoxCha.enableButtons();
        }

        editor.apply();
    }

    private void enableRollsPanel() {
        rootView.findViewById(R.id.panelTotalRolls).setVisibility(View.VISIBLE);
        rootView.findViewById(R.id.panelRolls).setVisibility(View.VISIBLE);
    }

    private void disableRollsPanel() {
        rootView.findViewById(R.id.panelTotalRolls).setVisibility(View.GONE);
        rootView.findViewById(R.id.panelRolls).setVisibility(View.GONE);
    }

    private void increaseRollCount() {
        totalRolls++;
        txtTotalRolls.setText(String.valueOf(totalRolls));
    }

    private void setRolls() {
        txtRandomAttribute1.setVisibility(View.VISIBLE);
        txtRandomAttribute1.setOnTouchListener(new ChoiceTouchListener());

        txtRandomAttribute2.setVisibility(View.VISIBLE);
        txtRandomAttribute2.setOnTouchListener(new ChoiceTouchListener());

        txtRandomAttribute3.setVisibility(View.VISIBLE);
        txtRandomAttribute3.setOnTouchListener(new ChoiceTouchListener());

        txtRandomAttribute4.setVisibility(View.VISIBLE);
        txtRandomAttribute4.setOnTouchListener(new ChoiceTouchListener());

        txtRandomAttribute5.setVisibility(View.VISIBLE);
        txtRandomAttribute5.setOnTouchListener(new ChoiceTouchListener());

        txtRandomAttribute6.setVisibility(View.VISIBLE);
        txtRandomAttribute6.setOnTouchListener(new ChoiceTouchListener());

        SharedPreferences sharedPref = getCharSharedPrefs();

        txtRandomAttribute1.setText(sharedPref.getString("rand1", ""));
        txtRandomAttribute2.setText(sharedPref.getString("rand2", ""));
        txtRandomAttribute3.setText(sharedPref.getString("rand3", ""));
        txtRandomAttribute4.setText(sharedPref.getString("rand4", ""));
        txtRandomAttribute5.setText(sharedPref.getString("rand5", ""));
        txtRandomAttribute6.setText(sharedPref.getString("rand6", ""));

        totalRolls = Integer.parseInt(sharedPref.getString("totalRolls", "0"));
        txtTotalRolls.setText(sharedPref.getString("totalRolls", "0"));
    }

    private void reRoll() {
        generateRolls();

        setRolls();
    }

    private void decreaseStat(EditableStatBox editableStatBox) {
        if (editableStatBox.getAttributeRoll() > editableStatBox.getMinimumValue()) {
            editableStatBox.setAttribute((editableStatBox.getAttributeRoll() - 1));
            setPointPool(getPointPool() + 1);
            editableStatBox.getTxtAttributeRoll().setText(String.valueOf(editableStatBox.getAttributeRoll()));
        }
    }

    private void increaseStat(EditableStatBox editableStatBox) {
        if (getPointPool() > 0 && editableStatBox.getAttributeRoll() < editableStatBox.getMaximumValue()) {
            editableStatBox.setAttribute(editableStatBox.getAttributeRoll() + 1);
            setPointPool(getPointPool() - 1);
            editableStatBox.getTxtAttributeRoll().setText(String.valueOf(editableStatBox.getAttributeRoll()));
        }
    }

    private int getPointPool() {
        return pointPool;
    }

    private void setPointPool(int pointPool) {
        this.pointPool = pointPool;
        txtPointPool.setText(String.valueOf(pointPool));
    }

    // Source: http://stackoverflow.com/questions/17344259/
    public void reset() {
        if (currentMode.equals(getStringResource(R.string.pref_method_roll))) {
            txtRandomAttribute1.setVisibility(View.VISIBLE);
            txtRandomAttribute2.setVisibility(View.VISIBLE);
            txtRandomAttribute3.setVisibility(View.VISIBLE);
            txtRandomAttribute4.setVisibility(View.VISIBLE);
            txtRandomAttribute5.setVisibility(View.VISIBLE);
            txtRandomAttribute6.setVisibility(View.VISIBLE);

            editableStatBoxStr.reset();
            editableStatBoxDex.reset();
            editableStatBoxCon.reset();
            editableStatBoxInt.reset();
            editableStatBoxWis.reset();
            editableStatBoxCha.reset();

            setPointPool(0);

            enableRolling();
        } else if (currentMode.equals(getStringResource(R.string.pref_method_pointbuy))){
            setMethodPointBuy();
        }
    }

    private void enableRolling() {
        btnRollNewValues.setVisibility(View.VISIBLE);
        btnResetRolls.setVisibility(View.GONE);
    }

    private void disableRolling() {
        btnRollNewValues.setVisibility(View.GONE);
        btnResetRolls.setVisibility(View.VISIBLE);
    }

    public void getRaceFromPrefs() {
        Gson gson = new Gson();
        String json = getCharSharedPrefs().getString(getResources().getString(R.string.C_CLASS_RACE), "");
        race = gson.fromJson(json, Race.class);
        setRaceModifiers();
    }

    @Override
    public void onInputClickOk(String text) {
        reset();

        reRoll();
    }

    @Override
    public void onInputClickCancel(String text) { }

    private String currentMode;

    private Race race;

    private View rootView;

    private int dice = 4;
    private int extraRolls = 0;
    private int pointPool;
    private int totalRolls;

    private EditableStatBox editableStatBoxStr;
    private EditableStatBox editableStatBoxDex;
    private EditableStatBox editableStatBoxCon;
    private EditableStatBox editableStatBoxInt;
    private EditableStatBox editableStatBoxWis;
    private EditableStatBox editableStatBoxCha;

    private TextView txtRandomAttribute1;
    private TextView txtRandomAttribute2;
    private TextView txtRandomAttribute3;
    private TextView txtRandomAttribute4;
    private TextView txtRandomAttribute5;
    private TextView txtRandomAttribute6;

    private TextView txtPointPool;
    private TextView txtTotalRolls;

    private Button btnResetRolls;
    private Button btnRollNewValues;

    /**
     * ChoiceTouchListener will handle touch events on draggable views
     *
     */
    private final class ChoiceTouchListener implements View.OnTouchListener {
        @SuppressLint("NewApi")
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            /*
             * Drag details: we only need default behavior
             * - clip data could be set to pass data as part of drag
             * - shadow can be tailored
             */
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * DragListener will handle dragged views being dropped on the drop area
     * - only the drop action will have processing added to it as we are not
     * - amending the default behavior for other parts of the drag process
     *
     */
    private class ChoiceDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                //no action necessary
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                //no action necessary
//                Toast.makeText(getActivity(), "ACTION_DRAG_ENTERED", Toast.LENGTH_SHORT).show();
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                //no action necessary
//                Toast.makeText(getActivity(), "ACTION_DRAG_EXITED", Toast.LENGTH_SHORT).show();
                break;
            case DragEvent.ACTION_DROP:

//                btnRollNewValues.setEnabled(false);
                disableRolling();
//                Toast.makeText(getActivity(), "ACTION_DROP", Toast.LENGTH_SHORT).show();
                //handle the dragged view being dropped over a drop view
                View view = (View) event.getLocalState();
                //stop displaying the view where it was before it was dragged
                view.setVisibility(View.INVISIBLE);
                //view dragged item is being dropped on
                TextView dropTarget = (TextView) v;
                //view being dragged and dropped
                TextView dropped = (TextView) view;
                //update the text in the target view to reflect the data being dropped
                dropTarget.setText(dropped.getText());
                //make it bold to highlight the fact that an item has been dropped
                dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
                //if an item has already been dropped here, there will be a tag
                Object tag = dropTarget.getTag();
                //if there is already an item here, set it back visible in its original place
                if(tag != null) {
                    //the tag is the view id already dropped here
                    int existingID = (Integer)tag;
                    //set the original view visible again
                    rootView.findViewById(existingID).setVisibility(View.VISIBLE);
                }
                //set the tag in the target view being dropped on - to the ID of the view being dropped
                dropTarget.setTag(dropped.getId());
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                //no action necessary
//                Toast.makeText(getActivity(), "ACTION_DRAG_ENDED", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
            }
            return true;
        }
    }
}
