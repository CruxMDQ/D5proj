package com.callisto.d5proj.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.callisto.d5proj.R;
import com.callisto.d5proj.interfaces.OnFeaturePickedListener;
import com.callisto.d5proj.interfaces.OnSpellPickedListener;
import com.callisto.d5proj.pojos.Feature;
import com.callisto.d5proj.pojos.Spell;

import java.util.ArrayList;

/**
 * Created by emiliano.desantis on 08/06/2015.
 */
public class SpellChoicesRVAdapter extends RecyclerView.Adapter<SpellChoicesRVAdapter.ChoiceRowHolder> {

    public SpellChoicesRVAdapter(ArrayList<Spell> options, OnSpellPickedListener listener) {
        this.options = options;
        this.listener = listener;
    }

    @Override
    public ChoiceRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_feature_choice, null);
        return new ChoiceRowHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ChoiceRowHolder choiceRowHolder, int i) {
        choiceRowHolder.spell = options.get(i);
        choiceRowHolder.chkFeatureChoice.setText(Html.fromHtml(options.get(i).getName()));
    }

    @Override
    public int getItemCount() {
        return (null != options ? options.size() : 0);
    }

    public class ChoiceRowHolder extends RecyclerView.ViewHolder {

        protected Spell spell;
        protected CheckBox chkFeatureChoice;
        protected OnSpellPickedListener listener;

        public ChoiceRowHolder(View view, final OnSpellPickedListener listener) {
            super(view);
            this.listener = listener;
            this.chkFeatureChoice = (CheckBox) view.findViewById(R.id.chkFeatureChoice);
            this.chkFeatureChoice.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        listener.onSpellPicked(spell);
                    }
                });
        }
    }

    private ArrayList<Spell> options;
    private OnSpellPickedListener listener;

}
