package com.example.ayurvedadiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SelectionActivity extends AppCompatActivity implements View.OnClickListener{
    public CardView card1,card2,card3,card4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);


        //Declaration of options provided in gridlayout
        card1=(CardView) findViewById(R.id.plant);
        card2=(CardView) findViewById(R.id.disease);
        card3=(CardView) findViewById(R.id.beauty);
        card4=(CardView) findViewById(R.id.yoga);

        //Applying setonclick listener
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        //Different cases provided. Clicking on one option will shift the user to the next assigned activity.
        switch (v.getId()){
            case R.id.plant:
                i = new Intent(this,PlantsActivity.class);
                startActivity(i);
                break;

            case R.id.disease:
                i = new Intent(this,DiseasesActivity.class);
                startActivity(i);
                break;

            case R.id.beauty:
                i = new Intent(this,BeautyActivity.class);
                startActivity(i);
                break;

            case R.id.yoga:
                i = new Intent(this,PlantsActivity.class);
                startActivity(i);
                break;
        }

    }
}