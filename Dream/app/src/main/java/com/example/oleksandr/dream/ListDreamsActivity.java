package com.example.oleksandr.dream;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.oleksandr.dream.Adapters.AdapterArrayDream;
import com.example.oleksandr.dream.DB.DBHelper;
import com.example.oleksandr.dream.DB.DreamDetails;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

public class ListDreamsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private DBHelper mDbHelper = null;
    private DreamDetails dreamDetails;
    private ListView mListView;
    private Button mButton;
    private Dao<DreamDetails, Integer> dreamDetailsDao;
    private List<DreamDetails> dreamList;
    private int selectedRecordPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dreams);
        mListView = (ListView) findViewById(R.id.listViewAllDreams);
        mButton = (Button) findViewById(R.id.buttonDelete);

        try {
            dreamDetailsDao = getHelper().getDreamDetailsesDao();
            dreamList = dreamDetailsDao.queryForAll();

            // Set the header of the ListView
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View view = inflater.inflate(R.layout.list_view, mListView, false);
            mListView.addHeaderView(view);

            // my own adapter!
           mListView.setAdapter(new AdapterArrayDream(this,R.layout.list_view,dreamList,dreamDetailsDao));
            mListView.setOnItemClickListener(this);



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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(i > 0)
        {
            selectedRecordPosition = i - 1;
            // Details screen showing code can put over here
            getId(selectedRecordPosition);

            final Intent intent = new Intent(this, ViewDream.class);
         //   intent.putExtra("Name",dreamList.get(selectedRecordPosition));
            Log.i("TAAAAAAG", "onClick insert " + dreamList.get(selectedRecordPosition));
            startActivity(intent);
        }
    }

    public void getId(int id){
        try {
            Where<DreamDetails, Integer> id1 = dreamDetailsDao.queryBuilder().where().eq("cMameDream", 1);

            Log.i("ID","   ififififififiififififi 111" + id1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) throws SQLException {
        mDbHelper.deleteAllData();
        finish();
        startActivity(getIntent());
    }
}
