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

import com.example.oleksandr.dream.DB.DBHelper;
import com.example.oleksandr.dream.DB.DreamDetails;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class NewDream extends AppCompatActivity implements View.OnClickListener {
    private DBHelper mDbHelper;
    private Button mButtonToList, mButtonSetTime;
    private EditText mEditdreamNAme, mEditTextDreamDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dream);
        mEditdreamNAme = (EditText)findViewById(R.id.editTextdreamName);
        mEditTextDreamDescription = (EditText) findViewById(R.id.editTextdreamDesriprion);
        mButtonToList = (Button)findViewById(R.id.to_list_button);
        mButtonSetTime =  (Button) findViewById(R.id.buttonSetTime);

    }
    public void onClickSetTime(View view) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view1 = inflater.inflate(R.layout.alarm_dialog_check_time, null);
        alertDialog.setView(view1);
        alertDialog.setMessage("Check time");
        alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        final  AlertDialog alertDialog2 = alertDialog.create();
        alertDialog2.show();
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
