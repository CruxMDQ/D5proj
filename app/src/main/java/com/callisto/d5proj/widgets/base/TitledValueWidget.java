package com.callisto.d5proj.widgets.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.callisto.d5proj.R;

/**
 * Created by Crux on 08/02/2015.
 */
public abstract class TitledValueWidget extends LinearLayout {
    private TextView txtAttributeName;
    private TextView txtAttributeRoll;
    private String attributeName;
    private int attributeValue;

    public TitledValueWidget(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray aAttrs = context
            .obtainStyledAttributes(attrs, R.styleable.StatBox, 0, 0);

        setAttributeName(aAttrs.getString(R.styleable.StatBox_attributeName));
        setAttributeValue(aAttrs.getInteger(R.styleable.StatBox_attributeRoll, 0));

        aAttrs.recycle();

        inflateLayout();

        getTxtAttributeName().setText(getAttributeName());
        getTxtAttributeRoll().setText(String.valueOf(getAttributeValue()));
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

    public void setTxtAttributeRoll(TextView txtAttributeRoll) {
        this.txtAttributeRoll = txtAttributeRoll;
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
        if (getTxtAttributeRoll() != null) {
            getTxtAttributeRoll().setText(String.valueOf(attributeValue));
        }
    }

    protected void inflateLayout() {
        inflate(this.getContext(), getLayout(), this);

        setTxtAttributeName((TextView) findViewById(R.id.txtAttributeName));
        setTxtAttributeRoll((TextView) findViewById(R.id.txtAttributeRoll));
    }

    protected abstract int getLayout();
}
