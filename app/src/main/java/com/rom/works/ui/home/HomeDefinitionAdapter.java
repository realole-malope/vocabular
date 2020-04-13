package com.rom.works.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.rom.works.R;
import com.rom.works.entity.Definition;

import java.util.List;

public class HomeDefinitionAdapter extends ArrayAdapter<Definition> implements View.OnClickListener {
    private List<Definition> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtWord;
        TextView txtExample;
        TextView txtMeaning;
    }

    public HomeDefinitionAdapter(List<Definition> data, Context context) {
        super(context, R.layout.definition_display_layout, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Definition object = getItem(position);
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Definition dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.definition_display_layout, parent, false);
            viewHolder.txtWord = convertView.findViewById(R.id.definition_name);
            viewHolder.txtExample = convertView.findViewById(R.id.definition_example);
            viewHolder.txtMeaning = convertView.findViewById(R.id.definition_meaning);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        lastPosition = position;

        viewHolder.txtWord.setText(dataModel.getWord());
        viewHolder.txtExample.setText(dataModel.getExample());
        viewHolder.txtMeaning.setText(dataModel.getMeaning());
        // Return the completed view to render on screen
        return convertView;
    }
}
