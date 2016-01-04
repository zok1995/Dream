package com.example.oleksandr.dream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oleksandr.dream.DB.DBHelper;
import com.example.oleksandr.dream.DB.DreamDetails;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class ViewDream extends AppCompatActivity {
    private TextView mDreanName;
    private EditText mDreamDescriptionText;
    private Dao<DreamDetails, Integer> dreamDetailsDao;
    private DBHelper mDbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dream);
        mDreanName = (TextView) findViewById(R.id.DreamName);
        mDreamDescriptionText = (EditText) findViewById(R.id.dream_description_text);

        String dreamName = getIntent().getStringExtra("Name");
        mDreanName.setText(dreamName);

    }
    private DBHelper getHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return mDbHelper;
    }
}
