package com.example.ayurvedadiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailActivity extends AppCompatActivity {

    TextView foodDescription;
    ImageView foodImage;
    TextView FoodTitle;
    String key="";
    String imageUrl="";

    //Detail activity ..once user click on the any product present in recycler view ..the description page is opened.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        foodDescription = (TextView)findViewById(R.id.txtDescription);
        foodImage = (ImageView)findViewById(R.id.ivImage2);
        FoodTitle = (TextView)findViewById(R.id.title);

        //Bundle is used to pass data between two activities.
        Bundle mBundle = getIntent().getExtras();
        if(mBundle!=null){

            //For showing the description, title and other entity from upload activity.
            foodDescription.setText(mBundle.getString("Description"));
            FoodTitle.setText(mBundle.getString("Title"));
            key = mBundle.getString("keyValue");
            imageUrl = mBundle.getString("Image");
            //foodImage.setImageResource(mbundle.getInt("Image"));

            //Glide for showing the image from what user has uploaded earlier.
            Glide.with(this)
                    .load(mBundle.getString("Image"))
                    .into(foodImage);
        }

    }

    //Delete option for deleting the product from firebase.
    public void btnDeleteRecipe(View view) {

        //Accessing the product from firebase
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Recipe");
        FirebaseStorage storage = FirebaseStorage.getInstance();

        //Accessing the image
        StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);

        //Deleting product from firebase and then a pop up message displays and shift the user to the recycler view where already some products are displayed.
        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                reference.child(key).removeValue();
                Toast.makeText(DetailActivity.this,"Product Deleted",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),uploadActivity.class));

                finish();

            }
        });


    }
}
