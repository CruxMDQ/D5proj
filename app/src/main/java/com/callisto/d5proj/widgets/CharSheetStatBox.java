package com.callisto.d5proj.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.callisto.d5proj.R;
import com.callisto.d5proj.widgets.base.TitledValueWidget;

/**
 * Widget intended for displaying stat score and modifier.
 * Created by emiliano.desantis on 06/02/2015.
 */
public class CharSheetStatBox extends TitledValueWidget {
    public CharSheetStatBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        calculateModifier();
    }

    @Override
    protected void inflateLayout() {
        super.inflateLayout();

        setTxtModifier((TextView) findViewById(R.id.txtModifier));
    }

    protected int getLayout() {
        return R.layout.widget_stat_box_charsheet;
    }

    public void setAttributeValue(int attributeValue) {
        super.setAttributeValue(attributeValue);
        if (getTxtModifier() != null) {
            calculateModifier();
        }
    }
    private TextView getTxtModifier() {
        return txtModifier;
    }

    private void setTxtModifier(TextView txtModifier) {
        this.txtModifier = txtModifier;
    }

    private int getModifier() {
        return modifier;
    }

    private void setModifier(int modifier) {
        this.modifier = modifier;
    }

    private void calculateModifier() {
        setModifier((getAttributeValue() - 10) / 2);
        getTxtModifier().setText(String.valueOf(getModifier()));
    }

    private TextView txtModifier;

    private int modifier;

}
