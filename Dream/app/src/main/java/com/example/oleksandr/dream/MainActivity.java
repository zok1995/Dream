package com.example.oleksandr.dream;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.oleksandr.dream.Adapters.AdapterArrayDream;
import com.example.oleksandr.dream.DB.DBHelper;
import com.example.oleksandr.dream.DB.DreamDetails;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {
    private DBHelper mDbHelper = null;
    private ListView mListView;
    private DrawerLayout drawerLayout;
    private Toolbar mToolbar;
    private AdapterArrayDream adapterArrayDream;
    private Dao<DreamDetails, Integer> dreamDetailsDao;
    private List<DreamDetails> dreamList;
    private int selectedRecordPosition = -1;
    private TextView textView;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listViewAllDreams);
        try {
            //Getting all Data from DB
            dreamDetailsDao = getHelper().getDreamDetailsesDao();
            dreamList = dreamDetailsDao.queryForAll();

            // Set the header of the ListView
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View view = inflater.inflate(R.layout.list_view, mListView, false);
            mListView.addHeaderView(view);
            adapterArrayDream = new AdapterArrayDream(this, R.layout.list_view, dreamList, dreamDetailsDao);

            // my own adapter!
            mListView.setAdapter(adapterArrayDream);
            mListView.setOnItemClickListener(this);
            mListView.setOnItemLongClickListener(this);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        initToolbar();
        initNavigationView();
        FloatingActionButton myFab = (FloatingActionButton)findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewDream.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(i > 0)
        {
            selectedRecordPosition = i - 1;
            /* Magick do not touch */
            LinearLayout linearLayout = (LinearLayout) view;
            textView = (TextView) linearLayout.findViewById(R.id.textViewDreamName);
            String string = textView.getText().toString();
            final Intent intent = new Intent(this, ViewDream.class);
            intent.putExtra("DATA",string);
            startActivity(intent);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0){
            selectedRecordPosition = position - 1;
            showAlertDialog();
        }
        return true;
    }

    private void showAlertDialog(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(R.string.deleteConfirmation);
        alertDialog.setPositiveButton(R.string.deleteDream, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    dreamDetailsDao.delete(dreamList.get(selectedRecordPosition));
                    dreamList.remove(selectedRecordPosition);
                    mListView.invalidate();
                    selectedRecordPosition = -1;
                    noRecord();
                    adapterArrayDream.notifyDataSetChanged();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        final  AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.show();
    }
    private void showAlertInfoDialog(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(R.string.developed_by);
        alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        final  AlertDialog alertDialog2 = alertDialog.create();
        alertDialog2.show();

    }

    private void noRecord() {
        if (dreamList.size() == 0){
            final TextView tv = new TextView(this);
            tv.setPadding(5, 5, 5, 5);
            tv.setTextSize(15);
            tv.setText("No Record Found !!");
            mListView.addFooterView(tv);
        }
    }


    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawlerLayout);
       NavigationView navigationView = (NavigationView) findViewById(R.id.navidation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.action_about:
                        showAlertInfoDialog();
                }
                return true;
            }
        });

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        mToolbar.inflateMenu(R.menu.menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_dreams, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private DBHelper getHelper() {
        if (mDbHelper == null) {
            mDbHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return mDbHelper;
    }


}























