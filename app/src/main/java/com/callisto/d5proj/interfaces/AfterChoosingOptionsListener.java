package com.callisto.d5proj.interfaces;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 10/06/2015.
 */
public interface AfterChoosingOptionsListener<T> {
    void afterChoosingOptions(Object feature, ArrayList<T> choices);
}
