package com.callisto.d5proj.interfaces;

import com.callisto.d5proj.pojos.Feature;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 10/06/2015.
 */
public interface AfterChoosingOptionsListener {
    void afterChoosingOptions(Feature feature, ArrayList<Feature> choices);
}
