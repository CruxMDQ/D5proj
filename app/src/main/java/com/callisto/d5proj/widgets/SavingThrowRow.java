package com.callisto.d5proj.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.callisto.d5proj.R;

/**
 * Created by Crux on 08/02/2015.
 */
public class SavingThrowRow extends LinearLayout {
    public SavingThrowRow(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray aAttrs = context
            .obtainStyledAttributes(attrs, R.styleable.StatBox, 0, 0);

        setAttributeName(aAttrs.getString(R.styleable.StatBox_attributeName));
        setSaveValue(aAttrs.getInteger(R.styleable.StatBox_attributeValue, 0));

        aAttrs.recycle();

        inflateLayout();

        getTxtTitleSave().setText(getAttributeName());
        getTxtSave().setText(String.valueOf(getSaveValue()));
    }

    private void inflateLayout() {
        inflate(this.getContext(), getLayout(), this);

        setTxtTitleSave((TextView) findViewById(R.id.titleSave));
        setTxtSave((TextView) findViewById(R.id.txtSave));
    }

    private int getLayout() {
        return R.layout.widget_row_saving_throw;
    }

    public TextView getTxtTitleSave() {
        return titleSave;
    }

    public void setTxtTitleSave(TextView titleSave) {
        this.titleSave = titleSave;
    }

    public TextView getTxtSave() {
        return txtSave;
    }

    public void setTxtSave(TextView txtSave) {
        this.txtSave = txtSave;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public int getSaveValue() {
        return saveValue;
    }

    public void setSaveValue(int saveValue) {
        this.saveValue = saveValue;
    }

    private TextView titleSave;
    private TextView txtSave;

    private String attributeName;
    private int saveValue = 0;
}
