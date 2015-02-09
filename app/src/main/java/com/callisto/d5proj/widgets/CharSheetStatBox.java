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
    public TextView getTxtModifier() {
        return txtModifier;
    }

    public void setTxtModifier(TextView txtModifier) {
        this.txtModifier = txtModifier;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public void calculateModifier() {
        setModifier((getAttributeValue() - 10) / 2);
        getTxtModifier().setText(String.valueOf(getModifier()));
    }

    private TextView txtModifier;

    private int modifier;

}
