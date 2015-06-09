package com.callisto.d5proj.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.callisto.d5proj.R;
import com.callisto.d5proj.pojos.Feature;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 08/06/2015.
 */
public class FeatureChoicesRVAdapter extends RecyclerView.Adapter<FeatureChoicesRVAdapter.ChoiceRowHolder> {

    public FeatureChoicesRVAdapter(Context context, ArrayList<Feature> features) {
        this.options = features;
        this.mContext = context;
    }

    @Override
    public ChoiceRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_feature_choice, null);
        return new ChoiceRowHolder(v);
    }

    @Override
    public void onBindViewHolder(ChoiceRowHolder choiceRowHolder, int i) {
        choiceRowHolder.option = options.get(i);
        choiceRowHolder.txtFeatureName.setText(Html.fromHtml(options.get(i).getName()));
    }

    @Override
    public int getItemCount() {
        return (null != options ? options.size() : 0);
    }

    private Context mContext;

    private ArrayList<Feature> options;

    public class ChoiceRowHolder extends RecyclerView.ViewHolder {

        protected Feature option;
        protected TextView txtFeatureName;

        public ChoiceRowHolder(View view) {
            super(view);
            this.txtFeatureName = (TextView) view.findViewById(R.id.txtFeatureName);
        }
    }
}
