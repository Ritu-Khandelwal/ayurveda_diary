package com.example.ayurvedadiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AlmondActivity extends AppCompatActivity {

    //Just logout option is there and some general info is there.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almond);


    }

    public void btnlogout(View view) {
            startActivity(new Intent(this,MainActivity.class));
        }
    }
