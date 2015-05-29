package com.callisto.d5proj.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.callisto.d5proj.R;

/**
 * Widget used on stat rolling fragment.
 * Created by emiliano.desantis on 05/02/2015.
 */
public class EditableStatBox extends LinearLayout{
    public EditableStatBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray aAttrs = context
            .obtainStyledAttributes(attrs, R.styleable.StatBox, 0, 0);

        setAttributeName(aAttrs.getString(R.styleable.StatBox_attributeName));
        setAttributeValue(aAttrs.getInteger(R.styleable.StatBox_attributeValue, 0));
        setMinimumValue(aAttrs.getInteger(R.styleable.StatBox_minimumValue, R.integer.MINIMUM_CHAR_STARTING_STAT));
        setMaximumValue(aAttrs.getInteger(R.styleable.StatBox_maximumValue, R.integer.MAXIMUM_CHAR_STARTING_STAT));

        aAttrs.recycle();

        inflateLayout();

        getTxtAttributeName().setText(getAttributeName());
        getTxtAttributeValue().setText(String.valueOf(getAttributeValue()));
    }

    private void inflateLayout() {
        inflate(this.getContext(), getLayout(), this);

        setTxtAttributeName((TextView) findViewById(R.id.txtAttributeName));
        setTxtAttributeValue((TextView) findViewById(R.id.txtAttributeValue));

        setBtnIncreaseValue((Button) findViewById(R.id.btnIncreaseValue));
        setBtnDecreaseValue((Button) findViewById(R.id.btnDecreaseValue));

        padEditStats = (LinearLayout) findViewById(R.id.padEditStats);
    }

    private int getLayout() {
        return R.layout.widget_stat_box_editable;
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

    public Button getBtnIncreaseValue() {
        return btnIncreaseValue;
    }

    public void setBtnIncreaseValue(Button btnIncreaseValue) {
        this.btnIncreaseValue = btnIncreaseValue;
    }

    public Button getBtnDecreaseValue() {
        return btnDecreaseValue;
    }

    public void setBtnDecreaseValue(Button btnDecreaseValue) {
        this.btnDecreaseValue = btnDecreaseValue;
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
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(int maximumValue) {
        this.maximumValue = maximumValue;
    }

    public int getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(int minimumValue) {
        this.minimumValue = minimumValue;
    }

    public void reset() {
        setAttributeValue(0);
        getTxtAttributeValue().setText(String.valueOf(getAttributeValue()));
        getTxtAttributeValue().setTag(null);
        getTxtAttributeValue().setTypeface(Typeface.DEFAULT);
    }

    public void toggleButtons() {
        if (padEditStats.getVisibility() == View.VISIBLE) {
            padEditStats.setVisibility(View.GONE);
        } else {
            padEditStats.setVisibility(View.VISIBLE);
        }
    }

    int minimumValue;
    int maximumValue;

    private TextView txtAttributeName;
    private TextView txtAttributeValue;

    private Button btnIncreaseValue;
    private Button btnDecreaseValue;

    private String attributeName;
    private int attributeValue;

    private LinearLayout padEditStats;

}
