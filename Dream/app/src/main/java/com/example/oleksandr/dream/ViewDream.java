package com.example.oleksandr.dream;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
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
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;

public class ViewDream extends AppCompatActivity {
    private TextView mDreanName,mTextViewDremaDescription;
    private EditText mEditTextName;
    private EditText mEditTextDescription;
    private Dao<DreamDetails, Integer> dreamDetailsDao;
    private DBHelper mDbHelper = null;
    private Button mButtonEdit;
    private String name,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dream);
        mDreanName = (TextView) findViewById(R.id.DreamName);
        mTextViewDremaDescription = (TextView) findViewById(R.id.textViewDescription);
        mButtonEdit = (Button) findViewById(R.id.buttonEdit);

        try {
            dreamDetailsDao = getHelper().getDreamDetailsesDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        description = intent.getStringExtra("DESCRIPTION");
        mDreanName.setText(name);
        mTextViewDremaDescription.setText(description);

    }

    public void onClickUpdate(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        View view1 = inflater.inflate(R.layout.alert_dialog_update_dream, null);

        mEditTextName = (EditText) view1.findViewById(R.id.editTextUpdateDreamName);
        mEditTextDescription = (EditText) view1.findViewById(R.id.editTextUpdateDreamDescription);

        builder.setView(view1);
        builder.setMessage("Editting Dream").setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    UpdateBuilder<DreamDetails, Integer> updateBuilder = dreamDetailsDao.updateBuilder();
                    updateBuilder.where().eq(DreamDetails.DREAM_NAME, name);
                    updateBuilder.where().eq(DreamDetails.DESCRIPTION,description);
                    updateBuilder.updateColumnValue(DreamDetails.DREAM_NAME, mEditTextName.getText().toString());
                    updateBuilder.updateColumnValue(DreamDetails.DESCRIPTION, mEditTextDescription.getText().toString());
                    updateBuilder.update();
                    Intent intent = new Intent(ViewDream.this, MainActivity.class);
                    startActivity(intent);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
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