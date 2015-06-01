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
        setAttributeRoll(aAttrs.getInteger(R.styleable.StatBox_attributeRoll, 0));
        setAttributeBonus(aAttrs.getInteger(R.styleable.StatBox_attributeBonus, 0));
        setMinimumValue(aAttrs
            .getInteger(R.styleable.StatBox_minimumValue, R.integer.MINIMUM_CHAR_STARTING_STAT));
        setMaximumValue(aAttrs
            .getInteger(R.styleable.StatBox_maximumValue, R.integer.MAXIMUM_CHAR_STARTING_STAT));

        aAttrs.recycle();

        inflateLayout();

        getTxtAttributeName().setText(getAttributeName());
        getTxtAttributeRoll().setText(String.valueOf(getAttributeRoll()));
    }

    private void inflateLayout() {
        inflate(this.getContext(), getLayout(), this);

        setTxtAttributeName((TextView) findViewById(R.id.txtAttributeName));
        setTxtAttributeRoll((TextView) findViewById(R.id.txtAttributeRoll));
        setTxtAttributeBonus((TextView) findViewById(R.id.txtAttributeBonus));
        setTxtAttributeTotal((TextView) findViewById(R.id.txtAttributeTotal));

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

    public TextView getTxtAttributeRoll() {
        return txtAttributeRoll;
    }

    public void setTxtAttributeRoll(TextView txtAttributeValue) {
        this.txtAttributeRoll = txtAttributeValue;
    }

    public TextView getTxtAttributeBonus() {
        return txtAttributeBonus;
    }

    public void setTxtAttributeBonus(TextView txtAttributeBonus) {
        this.txtAttributeBonus = txtAttributeBonus;
    }

    public TextView getTxtAttributeTotal() {
        return txtAttributeTotal;
    }

    public void setTxtAttributeTotal(TextView txtAttributeTotal) {
        this.txtAttributeTotal = txtAttributeTotal;
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

    public int getAttributeRoll() {
        return attributeRoll;
    }

    public void setAttributeRoll(int attributeRoll) {
        this.attributeRoll = attributeRoll;
    }

    public void setAttribute(int attributeRoll) {
        setAttributeRoll(attributeRoll);
        setAttributeTotal(attributeRoll + attributeBonus);
        if (attributeRoll == 0) {
            txtAttributeTotal.setVisibility(INVISIBLE);
        }
    }

    public int getAttributeTotal() {
        return attributeTotal;
    }

    public void setAttributeTotal(int attributeTotal) {
        this.attributeTotal = attributeTotal;
        if (attributeTotal > maximumValue) {
            txtAttributeTotal.setTextColor(getResources().getColor(android.R.color.holo_red_light));

        } else {
            txtAttributeTotal.setTextColor(getResources().getColor(android.R.color.black));
        }
        getTxtAttributeTotal().setText("" + attributeTotal);
        txtAttributeTotal.setVisibility(VISIBLE);
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

    public int getAttributeBonus() {
        return attributeBonus;
    }

    public void setAttributeBonus(int attributeBonus) {
        this.attributeBonus = attributeBonus;
        if (txtAttributeBonus != null) {
            if (attributeBonus == 0) {
                txtAttributeBonus.setVisibility(INVISIBLE);
            } else {
                txtAttributeBonus.setVisibility(VISIBLE);
            }
            txtAttributeBonus.setText("+" + attributeBonus);
        }
    }

    public void reset() {
        setAttributeRoll(0);
        getTxtAttributeRoll().setText(String.valueOf(getAttributeRoll()));
        getTxtAttributeRoll().setTag(null);
        getTxtAttributeRoll().setTypeface(Typeface.DEFAULT);
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
    private TextView txtAttributeRoll;
    private TextView txtAttributeBonus;
    private TextView txtAttributeTotal;

    private Button btnIncreaseValue;
    private Button btnDecreaseValue;

    private String attributeName;
    private int attributeRoll;
    private int attributeBonus;
    private int attributeTotal;

    private LinearLayout padEditStats;

}
