package com.example.ayurvedadiary;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AmlaActivity extends AppCompatActivity {

    //Just logout option is there and some general info is there and along with this upload option is also provided.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amla);


    }

    public void btnnext(View view) {startActivity(new Intent(this,uploadActivity.class));
    }

    public void btnlogout(View view) {startActivity(new Intent(this, MainActivity.class));
    }
}
