package com.example.oleksandr.dream;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        if (mEditTextEnterDream.getText().toString().matches("")){
            Toast.makeText(MainActivity.this,"Type you Dream!",Toast.LENGTH_LONG).show();
            }else {
            if (view == mButtonNext){
                startActivity(new Intent(this, NewDream.class));
                Intent intent = new Intent(MainActivity.this, NewDream.class);
                // указываем первым параметром ключ, а второе значение
                // по ключу мы будем получать значение с Intent
                intent.putExtra("Dream", mEditTextEnterDream.getText().toString());
                // показываем новое Activity
                startActivity(intent);
//                Log.i("TAAAAAAG", "ELSE " );
            }
        }
    }
}
