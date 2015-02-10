package com.callisto.d5proj.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.callisto.d5proj.R;
import com.callisto.d5proj.pojos.CharacterClass;

import java.util.List;

/**
 * Created by emiliano.desantis on 10/02/2015.
 */
public class ClassSelectorAdapter extends ArrayAdapter<CharacterClass> {
    private Context context;
    private List<CharacterClass> characterClasses;
    private LayoutInflater inflater = null;

    public ClassSelectorAdapter(Context context, List<CharacterClass> items) {
        super(context, 0, items);

        this.context = context;
        this.characterClasses = items;

        this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        ViewHolder viewHolder = null;

        final String name = characterClasses.get(position).getName();

        if (rowView == null){
            rowView = inflater.inflate(R.layout.row_class_selector, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.txtClassName = (TextView) rowView.findViewById(R.id.txtClassName);

            rowView.setTag(viewHolder);
        } else {
            rowView = convertView;

            viewHolder = ((ViewHolder) rowView.getTag());
        }

        viewHolder.txtClassName.setText(name);

        return rowView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        ViewHolder viewHolder = null;

        final String name = characterClasses.get(position).getName();

        if (rowView == null){
            rowView = inflater.inflate(R.layout.row_class_selector, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.txtClassName = (TextView) rowView.findViewById(R.id.txtClassName);

            rowView.setTag(viewHolder);
        } else {
            rowView = convertView;

            viewHolder = ((ViewHolder) rowView.getTag());
        }

        viewHolder.txtClassName.setText(name);

        return rowView;
    }

    class ViewHolder {
        TextView txtClassName;
    }
}
