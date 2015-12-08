package com.example.oleksandr.dream;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oleksandr.dream.DB.DBHelper;
import com.example.oleksandr.dream.DB.DreamDetails;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class NewDream extends AppCompatActivity implements View.OnClickListener {
    private DBHelper mDbHelper;
    private Button to_list_button;
    public String TRY = "FIRST DREAM";
    private TextView Dream_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dream);
        Dream_text = (TextView) findViewById(R.id.Dream_text);
        String txtDream = getIntent().getStringExtra("Dream");
        Dream_text.setText(Dream_text.getText().toString() + " " + txtDream);

        to_list_button = (Button)findViewById(R.id.to_list_button);
        to_list_button.setOnClickListener(this);
        try {
            final Dao<DreamDetails, Integer> daoDream = getHelper().getDreamDetailsesDao();
            //TODO add list with dream + adapter

        } catch (SQLException e) {
            e.printStackTrace();
        }

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
            startActivity(new Intent(this, ListDreamsActivity.class));
            final DreamDetails dreamDetails = new DreamDetails();
            dreamDetails.dreamName = TRY;
            Log.i("TAAAAAAG", "onClick ");
            try {
                //Insert do DB
                final Dao<DreamDetails, Integer> daoDream = getHelper().getDreamDetailsesDao();
                daoDream.create(dreamDetails);
                Log.i("TAAAAAAG", "onClick insert");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
