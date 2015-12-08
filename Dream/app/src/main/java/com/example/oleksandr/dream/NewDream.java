package com.example.oleksandr.dream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.oleksandr.dream.DB.DBHelper;
import com.example.oleksandr.dream.DB.DreamDetails;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class NewDream extends AppCompatActivity {
    private DBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dream);

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
}
