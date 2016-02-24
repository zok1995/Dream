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
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;

public class ViewDream extends AppCompatActivity {
    private TextView mDreanName;
    private EditText mDreamDescriptionText;
    private Dao<DreamDetails, Integer> dreamDetailsDao;
    private DBHelper mDbHelper = null;
    private Button mButtonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dream);
        mDreanName = (TextView) findViewById(R.id.DreamName);
        mDreamDescriptionText = (EditText) findViewById(R.id.dream_description_text);
        mButtonEdit = (Button) findViewById(R.id.buttonEdit);
        Intent intent = getIntent();
        String a = intent.getStringExtra("DATA");
        mDreanName.setText(a);

    }

    public void onClick(View view) throws SQLException {
        DeleteBuilder<DreamDetails, Integer> dreamDetailsIntegerDeleteBuilder = dreamDetailsDao.deleteBuilder();
        dreamDetailsIntegerDeleteBuilder.where().eq("cMameDream", "PORNO");
        dreamDetailsIntegerDeleteBuilder.delete();
        finish();
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private DBHelper getHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return mDbHelper;
    }

}
