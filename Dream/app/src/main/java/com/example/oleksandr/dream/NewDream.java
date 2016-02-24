package com.example.oleksandr.dream;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.oleksandr.dream.DB.DBHelper;
import com.example.oleksandr.dream.DB.DreamDetails;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class NewDream extends AppCompatActivity implements View.OnClickListener {
    private DBHelper mDbHelper;
    private Button to_list_button;
    private EditText mEditdreamNAme, mEditTextDreamDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dream);
        mEditdreamNAme = (EditText)findViewById(R.id.editTextdreamName);
        mEditTextDreamDescription = (EditText) findViewById(R.id.editTextdreamDesriprion);
        to_list_button = (Button)findViewById(R.id.to_list_button);


    }
    private DBHelper getHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return mDbHelper;
    }

    @Override
    public void onClick(View v) {
        if (v == to_list_button) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
