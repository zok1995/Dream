package com.example.oleksandr.dream;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
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
    private TextView mDreanName,mTextViewDremaDescription;
    private Dao<DreamDetails, Integer> dreamDetailsDao;
    private DBHelper mDbHelper = null;
    private Button mButtonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dream);
        mDreanName = (TextView) findViewById(R.id.DreamName);
        mTextViewDremaDescription = (TextView) findViewById(R.id.textViewDescription);
        mButtonEdit = (Button) findViewById(R.id.buttonEdit);
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String description = intent.getStringExtra("DESCRIPTION");
        mDreanName.setText(name);
        mTextViewDremaDescription.setText(description);

    }

    public void onClick(View view) throws SQLException {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.alert_dialog_update_dream, null));
        builder.setMessage("Editting Dream").setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("Cancle",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private DBHelper getHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return mDbHelper;
    }

}
/*
        DeleteBuilder<DreamDetails, Integer> dreamDetailsIntegerDeleteBuilder = dreamDetailsDao.deleteBuilder();
        dreamDetailsIntegerDeleteBuilder.where().eq("cMameDream", "PORNO");
        dreamDetailsIntegerDeleteBuilder.delete();
        finish();
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
 */