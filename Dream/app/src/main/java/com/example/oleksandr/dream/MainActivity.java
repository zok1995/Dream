package com.example.oleksandr.dream;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mButtonNext;
    private EditText mEditTextEnterDream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonNext = (Button) findViewById(R.id.dream_button);
        mEditTextEnterDream = (EditText) findViewById(R.id.editTextEnterDream);

        mButtonNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mButtonNext){
            startActivity(new Intent(this, NewDream.class));
            Intent intent = new Intent(MainActivity.this, NewDream.class);
            // указываем первым параметром ключ, а второе значение
            // по ключу мы будем получать значение с Intent
            intent.putExtra("Dream", mEditTextEnterDream.getText().toString());
            // показываем новое Activity
            startActivity(intent);
        }
    }
    public void goNewView(View v){
        switch (v.getId()) {
            case R.id.editTextEnterDream:
                // Говорим между какими Activity будет происходить связь

                Log.i("DATA FROM EDIT TEXT", "Entered " + mEditTextEnterDream.getText().toString());
                break;
            default:
                break;
        }
    }
}
