package com.example.oleksandr.dream;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.oleksandr.dream.DB.DBHelper;
import com.example.oleksandr.dream.DB.DreamDetails;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class NewDream extends AppCompatActivity implements View.OnClickListener {
    private DBHelper mDbHelper;
    private Button mButtonToList;
    private TimePicker mTimePicker;
    private EditText mEditdreamNAme, mEditTextDreamDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dream);
        mEditdreamNAme = (EditText)findViewById(R.id.editTextdreamName);
        mEditTextDreamDescription = (EditText) findViewById(R.id.editTextdreamDesriprion);
        mButtonToList = (Button)findViewById(R.id.to_list_button);
        mTimePicker = (TimePicker) findViewById(R.id.timePicker);
        mTimePicker.setIs24HourView(true);

    }

    @Override
    public void onClick(View v) {
        if (v == mButtonToList) {
            startActivity(new Intent(this, MainActivity.class));
            final DreamDetails dreamDetails = new DreamDetails();
            dreamDetails.dreamName = mEditdreamNAme.getText().toString();
            dreamDetails.descriptionDream = mEditTextDreamDescription.getText().toString();
           Log.i("TAAAAAAG", "onClick ");
            try {
                //Insert do DB
                final Dao<DreamDetails, Integer> daoDream = getHelper().getDreamDetailsesDao();
                daoDream.create(dreamDetails);
                Log.i("TAAAAAAG", "onClick insert " + mEditdreamNAme.getText().toString());
                int hour = mTimePicker.getCurrentHour();
                int minute = mTimePicker.getCurrentMinute();
                Log.i("TAAAAAAG", "Time picked " + hour + " " + minute);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private DBHelper getHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return mDbHelper;
    }

}
