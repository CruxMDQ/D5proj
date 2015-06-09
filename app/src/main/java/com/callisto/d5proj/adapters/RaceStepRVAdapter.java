package com.callisto.d5proj.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.callisto.d5proj.R;
import com.callisto.d5proj.pojos.Feature;
import com.callisto.d5proj.pojos.Race;

/**
 * Created by emiliano.desantis on 08/06/2015.
 */
public class RaceStepRVAdapter extends RecyclerView.Adapter<RaceStepRVAdapter.StringRowHolder> {

    private Race race;
    private Activity mActivity;

    public RaceStepRVAdapter(Activity activity, Race race) {
        this.race = race;
        this.mActivity = activity;
    }

    @Override
    public StringRowHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_race_feature, null);
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager = ((FragmentActivity) mActivity).getSupportFragmentManager();
//                PickChoicesDFragment fragment = new PickChoicesDFragment();
//                fragment.setOptions(race.getRacialFeatures().get(i).getFeatureChoices());
//                fragmentManager.beginTransaction()
//                    .replace(R.id.container, new PickChoicesDFragment())
//                    .commit();
//            }
//        });
        return new StringRowHolder(v);
    }

    @Override
    public void onBindViewHolder(StringRowHolder stringRowHolder, int i) {
//        String title = race.getRacialFeatures().get(i).getName();

        Feature f = race.getRacialFeatures().get(i);

        if (f.getChoices() > 0) {
            stringRowHolder.txtFeatureName.setBackgroundColor(mActivity.getResources().getColor(R.color.bkgr_feature_choices_pending));
        }

        stringRowHolder.txtFeatureName.setText(Html.fromHtml(race.getRacialFeatures().get(i).getName()));

    }

    @Override
    public int getItemCount() {
        return (null != race.getRacialFeatures() ? race.getRacialFeatures().size() : 0);
    }

    public class StringRowHolder extends RecyclerView.ViewHolder {
        protected TextView txtFeatureName;

        public StringRowHolder(View view) {
            super(view);
            this.txtFeatureName = (TextView) view.findViewById(R.id.txtFeatureName);
        }
    }
}
