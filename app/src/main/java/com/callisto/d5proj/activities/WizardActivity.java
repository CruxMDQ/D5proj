package com.callisto.d5proj.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.callisto.d5proj.R;

/**
 * Created by emiliano.desantis on 07/05/2015.
 */
public class WizardActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);
    }
    // Following snippet useful for checking sw value on emulator.
    // Source: http://android4beginners.com/2013/07/appendix-c-everything-about-sizes-and-dimensions-in-android/

//    if (getResources().getConfiguration().smallestScreenWidthDp >= 600)
//    {
//        setContentView(R.layout.activity_main_tablet);
//    }
//    else
//    {
//        setContentView(R.layout.activity_main);
//    }
}
