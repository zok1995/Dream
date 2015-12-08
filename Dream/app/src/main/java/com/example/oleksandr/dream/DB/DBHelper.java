package com.example.oleksandr.dream.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.oleksandr.dream.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Oleksandr on 06.12.2015.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "dream.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<DreamDetails, Integer> dreamDetailsesDao;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, DreamDetails.class);
            Log.e("OnCreate DB", "DB created " + DATABASE_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(DBHelper.class.getName(), "Unable to create datbases", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, DreamDetails.class, true);
            onCreate(database, connectionSource);
            Log.e("onUpgrade DB", "DB Updated" + DATABASE_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(DBHelper.class.getName(), "Unable to upgrade database from version " + oldVersion + " to new "
                    + newVersion, e);
        }
    }

    public Dao<DreamDetails, Integer> getDreamDetailsesDao() throws SQLException {
        if (dreamDetailsesDao == null){
            dreamDetailsesDao = getDao(DreamDetails.class);
        }
        return dreamDetailsesDao;
    }
}
