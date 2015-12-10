package com.example.oleksandr.dream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ViewDream extends AppCompatActivity {

    private EditText mViewDreaTtext;
    private EditText mDreamDescriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dream);
        mViewDreaTtext = (EditText) findViewById(R.id.view_dream_text);
        mDreamDescriptionText = (EditText) findViewById(R.id.dream_description_text);
    }
}
