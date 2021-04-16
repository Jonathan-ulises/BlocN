package com.macrobios.blocn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent toChange = new Intent(MainActivity.this, Principal.class);
                startActivity(toChange);
                finish();
            }
        }, 4000);


    }
}