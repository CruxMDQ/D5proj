package com.callisto.d5proj.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.callisto.d5proj.R;
import com.callisto.d5proj.interfaces.OnChoosingOptionsListener;
import com.callisto.d5proj.pojos.Feature;
import com.callisto.d5proj.pojos.Race;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 08/06/2015.
 */
public class RaceStepRVAdapter extends RecyclerView.Adapter<RaceStepRVAdapter.StringRowHolder> {

    public RaceStepRVAdapter(Activity activity, Race race, OnChoosingOptionsListener listener) {
        this.mActivity = activity;
        this.listener = listener;
        this.features = race.getRacialFeatures();
    }

    public RaceStepRVAdapter(Activity activity, ArrayList<Feature> features, OnChoosingOptionsListener listener) {
        this.mActivity = activity;
        this.listener = listener;
        this.features = features;
    }

    @Override
    public StringRowHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_race_feature, null);
        return new StringRowHolder(v);
    }

    @Override
    public void onBindViewHolder(StringRowHolder stringRowHolder, int i) {
        final Feature featureWithChoices = features.get(i);

        if (featureWithChoices.getChoices() > 0) {
            stringRowHolder.txtFeatureName.setBackgroundColor(mActivity.getResources().getColor(R.color.bkgr_feature_choices_pending));
            stringRowHolder.txtFeatureName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onInputClick(featureWithChoices);
                }
            });
        }

        stringRowHolder.txtFeatureName.setText(Html.fromHtml(features.get(i).getName()));

    }

    @Override
    public int getItemCount() {
        return (null != features ? features.size() : 0);
    }

    public class StringRowHolder extends RecyclerView.ViewHolder {
        protected TextView txtFeatureName;

        public StringRowHolder(View view) {
            super(view);
            this.txtFeatureName = (TextView) view.findViewById(R.id.txtFeatureName);
        }
    }

    private Activity mActivity;
    private ArrayList<Feature> features;
    private OnChoosingOptionsListener listener;

}
