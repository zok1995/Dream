package com.example.oleksandr.dream.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.oleksandr.dream.DB.DreamDetails;
import com.example.oleksandr.dream.R;
import com.j256.ormlite.dao.Dao;

import java.util.List;

/**
 * Created by Oleksandr on 14.12.2015.
 */
public class AdapterArrayDream extends ArrayAdapter<String> {
    private LayoutInflater inflater;
    private List records;
    private Dao<DreamDetails, Integer> dreamDetailsDao;
    public AdapterArrayDream(Context context, int resource, List list, Dao<DreamDetails, Integer> teacherDao) {
        super(context, resource,list);
        this.records = list;
        this.dreamDetailsDao = teacherDao;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Reuse the view to make the scroll effect smooth
        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_view, parent, false);
        }

        if (records.get(position).getClass().isInstance(new DreamDetails())){
             final DreamDetails dreamDetails = (DreamDetails) records.get(position);
//            dreamDetailsDao.refresh(dreamDetails.)

            ((TextView) convertView.findViewById(R.id.textViewDreamName)).setText(dreamDetails.dreamName);
            ((TextView) convertView.findViewById(R.id.textViewDreamDescription)).setText(dreamDetails.descriptionDream);
        }else {
            Log.i("EROR", "ADAPTER ERROR");
        }
        return  convertView;
    }
}
