package com.example.ayurvedadiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //widgets
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;
    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);

        //Animation
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.imageView5);
        logo = findViewById(R.id.textView6);
        slogan = findViewById(R.id.textView4);


        image.setAnimation(topAnim);
        logo.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);





        }


    }

