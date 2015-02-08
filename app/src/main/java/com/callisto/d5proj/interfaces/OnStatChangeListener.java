package com.callisto.d5proj.interfaces;

import com.callisto.d5proj.enums.BaseStatistic;

/**
 * Interface for communication between base stats and derived stats fragments.
 * Created by Crux on 07/02/2015.
 */
public interface OnStatChangeListener {
    void onStatChange(BaseStatistic stat, int value);
}
