package com.callisto.d5proj.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.callisto.d5proj.R;
import com.callisto.d5proj.widgets.base.TitledValueWidget;

/**
 * Created by Crux on 08/02/2015.
 */
public class SavingThrowRow extends TitledValueWidget {
    public SavingThrowRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected int getLayout() {
        return R.layout.widget_row_saving_throw;
    }
}
