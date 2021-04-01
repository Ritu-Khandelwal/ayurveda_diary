package com.example.ayurvedadiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UlcerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulcer);
    }

    public void btnlogout(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}
