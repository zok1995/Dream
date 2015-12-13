package com.example.oleksandr.dream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.oleksandr.dream.DB.DBHelper;
import com.example.oleksandr.dream.DB.DreamDetails;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class ListDreamsActivity extends AppCompatActivity {
    private DBHelper mDbHelper = null;
    private TextView mTextView;
    private Dao<DreamDetails, Integer> dreamDetailsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dreams);

        mTextView = (TextView) findViewById(R.id.textViewTryReadFromDB);

        try {
            dreamDetailsDao = getHelper().getDreamDetailsesDao();
            Log.i("DATA FROM DB-----", "Data:" + dreamDetailsDao.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // This is how, DatabaseHelper can be initialized for future use
    private DBHelper getHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return mDbHelper;
    }
}
