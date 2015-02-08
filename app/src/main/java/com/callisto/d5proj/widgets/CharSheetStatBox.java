package com.callisto.d5proj.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.callisto.d5proj.R;

/**
 * Widget intended for displaying stat score and modifier.
 * Created by emiliano.desantis on 06/02/2015.
 */
public class CharSheetStatBox extends LinearLayout {
    public CharSheetStatBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray aAttrs = context
            .obtainStyledAttributes(attrs, R.styleable.StatBox, 0, 0);

        setAttributeName(aAttrs.getString(R.styleable.StatBox_attributeName));
        setAttributeValue(aAttrs.getInteger(R.styleable.StatBox_attributeValue, 0));

        aAttrs.recycle();

        inflateLayout();

        getTxtAttributeName().setText(getAttributeName());
        getTxtAttributeValue().setText(String.valueOf(getAttributeValue()));
        calculateModifier();
    }

    private void inflateLayout() {
        inflate(this.getContext(), getLayout(), this);

        setTxtAttributeName((TextView) findViewById(R.id.txtAttributeName));
        setTxtAttributeValue((TextView) findViewById(R.id.txtAttributeValue));
        setTxtModifier((TextView) findViewById(R.id.txtModifier));
    }

    private int getLayout() {
        return R.layout.widget_stat_box_charsheet;
    }

    public TextView getTxtAttributeName() {
        return txtAttributeName;
    }

    public void setTxtAttributeName(TextView txtAttributeName) {
        this.txtAttributeName = txtAttributeName;
    }

    public TextView getTxtAttributeValue() {
        return txtAttributeValue;
    }

    public void setTxtAttributeValue(TextView txtAttributeValue) {
        this.txtAttributeValue = txtAttributeValue;
    }

    public TextView getTxtModifier() {
        return txtModifier;
    }

    public void setTxtModifier(TextView txtModifier) {
        this.txtModifier = txtModifier;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public int getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(int attributeValue) {
        this.attributeValue = attributeValue;
        if (getTxtAttributeValue() != null) {
            getTxtAttributeValue().setText(String.valueOf(this.attributeValue));
        }
        if (getTxtModifier() != null) {
            calculateModifier();
        }
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

    private TextView txtAttributeName;
    private TextView txtAttributeValue;
    private TextView txtModifier;

    private String attributeName;
    private int attributeValue;
    private int modifier;

}
