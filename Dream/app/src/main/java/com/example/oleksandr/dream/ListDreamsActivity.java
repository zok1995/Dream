package com.example.oleksandr.dream;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.oleksandr.dream.DB.DBHelper;
import com.example.oleksandr.dream.DB.DreamDetails;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class ListDreamsActivity extends AppCompatActivity {
    private DBHelper mDbHelper = null;
    private TextView mTextView;
    private ListView mListView;
    private Dao<DreamDetails, Integer> dreamDetailsDao;
    private List<DreamDetails> dreamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dreams);
        mListView = (ListView) findViewById(R.id.listViewAllDreams);
        mTextView = (TextView) findViewById(R.id.textViewTryReadFromDB);

        try {
            dreamDetailsDao = getHelper().getDreamDetailsesDao();
            dreamList = dreamDetailsDao.queryForAll();

            // Set the header of the ListView
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View view = inflater.inflate(R.layout.list_view, mListView, false);
            mListView.addHeaderView(view);

            // TODO  Create my own adapter!
           // mListView.setAdapter(new Rec);

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
