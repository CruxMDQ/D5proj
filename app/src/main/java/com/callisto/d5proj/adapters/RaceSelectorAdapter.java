package com.callisto.d5proj.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.callisto.d5proj.R;
import com.callisto.d5proj.pojos.Race;

import java.util.List;

public class RaceSelectorAdapter extends ArrayAdapter<Race> {
    private List<Race> races;
    private LayoutInflater inflater = null;

    public RaceSelectorAdapter(Context context, List<Race> items) {
        super(context, 0, items);

        this.races = items;

        this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        ViewHolder viewHolder;

        final String name = races.get(position).getName();

        if (rowView == null){
            rowView = inflater.inflate(R.layout.row_class_selector, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.txtRaceName = (TextView) rowView.findViewById(R.id.txtClassName);

            rowView.setTag(viewHolder);
        } else {
            rowView = convertView;

            viewHolder = ((ViewHolder) rowView.getTag());
        }

        viewHolder.txtRaceName.setText(name);

        return rowView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        ViewHolder viewHolder;

        final String name = races.get(position).getName();

        if (rowView == null){
            rowView = inflater.inflate(R.layout.row_race_selector, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.txtRaceName = (TextView) rowView.findViewById(R.id.txtRaceName);

            rowView.setTag(viewHolder);
        } else {
            rowView = convertView;

            viewHolder = ((ViewHolder) rowView.getTag());
        }

        viewHolder.txtRaceName.setText(name);

        return rowView;
    }

    class ViewHolder {
        TextView txtRaceName;
    }
}
