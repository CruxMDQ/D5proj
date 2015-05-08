package com.callisto.d5proj.wizard.steps;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.callisto.d5proj.R;
import com.callisto.d5proj.db.tables.CharacterClasses;
import com.callisto.d5proj.enums.BaseStatistic;
import com.callisto.d5proj.interfaces.OnStatChangeListener;
import com.callisto.d5proj.tools.DiceRoller;
import com.callisto.d5proj.widgets.EditableStatBox;

import org.codepond.wizardroid.WizardStep;

public class StatAllocationStep extends WizardStep {

    private CharacterClasses dbCharacterClasses;

    public StatAllocationStep() { super(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_stat_edition, container, false);

        dbCharacterClasses = new CharacterClasses(this.getActivity());

        findComponents(rootView);

        if (!hasPreviousRolls()) {
            generateRolls();
        }

        setRolls();

        return rootView;
    }

    private SharedPreferences getCharSharedPrefs() {
        Context context = getActivity();
        return context.getSharedPreferences(
            getString(R.string.character_stats), Context.MODE_PRIVATE);
    }

    private void findComponents(View rootView) {
        btnResetRolls = (Button) rootView.findViewById(R.id.btnResetRolls);
        btnResetRolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

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
        prepareStatBox(editableStatBoxStr, BaseStatistic.STR);

        editableStatBoxDex = (EditableStatBox) rootView.findViewById(R.id.statBoxDex);
        prepareStatBox(editableStatBoxDex, BaseStatistic.DEX);

        editableStatBoxCon = (EditableStatBox) rootView.findViewById(R.id.statBoxCon);
        prepareStatBox(editableStatBoxCon, BaseStatistic.CON);

        editableStatBoxInt = (EditableStatBox) rootView.findViewById(R.id.statBoxInt);
        prepareStatBox(editableStatBoxInt, BaseStatistic.INT);

        editableStatBoxWis = (EditableStatBox) rootView.findViewById(R.id.statBoxWis);
        prepareStatBox(editableStatBoxWis, BaseStatistic.WIS);

        editableStatBoxCha = (EditableStatBox) rootView.findViewById(R.id.statBoxCha);
        prepareStatBox(editableStatBoxCha, BaseStatistic.CHA);
    }

    private void prepareStatBox(final EditableStatBox editableStatBox, final BaseStatistic baseStatistic) {
        editableStatBox.getTxtAttributeValue().setOnDragListener(new ChoiceDragListener());

        // Source: http://stackoverflow.com/questions/20824634/
        editableStatBox.getTxtAttributeValue().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                int attributeValue = Integer.parseInt(s.toString());
                editableStatBox.setAttributeValue(attributeValue);
                onStatChangeListener.onStatChange(baseStatistic, attributeValue);
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
    }

    private boolean hasPreviousRolls() {
        return !getCharSharedPrefs().getAll().isEmpty();
    }

    private void generateRolls() {
        SharedPreferences sharedPref = getCharSharedPrefs();

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("rand1", String.valueOf(DiceRoller.rollDice(4, 6, 1)));
        editor.putString("rand2", String.valueOf(DiceRoller.rollDice(4, 6, 1)));
        editor.putString("rand3", String.valueOf(DiceRoller.rollDice(4, 6, 1)));
        editor.putString("rand4", String.valueOf(DiceRoller.rollDice(4, 6, 1)));
        editor.putString("rand5", String.valueOf(DiceRoller.rollDice(4, 6, 1)));
        editor.putString("rand6", String.valueOf(DiceRoller.rollDice(4, 6, 1)));

        increaseRollCount();

        editor.putString("totalRolls", String.valueOf(totalRolls));

        editor.apply();
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

    void decreaseStat(EditableStatBox editableStatBox) {
        if (editableStatBox.getAttributeValue() > 3) {
            editableStatBox.setAttributeValue((editableStatBox.getAttributeValue() - 1));
            setPointPool(getPointPool() + 1);
            editableStatBox.getTxtAttributeValue().setText(String.valueOf(editableStatBox.getAttributeValue()));
        }
    }

    void increaseStat(EditableStatBox editableStatBox) {
        if (getPointPool() > 0 && editableStatBox.getAttributeValue() < 18) {
            editableStatBox.setAttributeValue(editableStatBox.getAttributeValue() + 1);
            setPointPool(getPointPool() - 1);
            editableStatBox.getTxtAttributeValue().setText(String.valueOf(editableStatBox.getAttributeValue()));
        }
    }

    int getPointPool() {
        return pointPool;
    }

    void setPointPool(int pointPool) {
        this.pointPool = pointPool;
        txtPointPool.setText(String.valueOf(pointPool));
    }

    // Source: http://stackoverflow.com/questions/17344259/
    void reset() {
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
    }

    private void enableRolling() {
        btnRollNewValues.setVisibility(View.VISIBLE);
        btnResetRolls.setVisibility(View.GONE);
    }

    private void disableRolling() {
        btnRollNewValues.setVisibility(View.GONE);
        btnResetRolls.setVisibility(View.VISIBLE);
    }

    private View rootView;

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

    OnStatChangeListener onStatChangeListener;
    //    private LinearLayout panelRolls;

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
