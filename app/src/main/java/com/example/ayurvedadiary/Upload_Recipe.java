package com.example.ayurvedadiary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.text.DateFormat;
import java.util.Calendar;

public class Upload_Recipe extends AppCompatActivity {

    ImageView recipeImage;
    Uri uri;
    EditText txt_name,txt_description,txt_price;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__recipe);

        //Declaring variables
        recipeImage = (ImageView)findViewById(R.id.iv_productImage);
        txt_name = (EditText)findViewById(R.id.txt_recipe_name);
        txt_description = (EditText)findViewById(R.id.text_description);
        txt_price = (EditText)findViewById(R.id.text_price);
    }

    //Clicking on select image option allows user to choose image from gallery.
    public void btnSelectImage(View view) {

        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);

    }

    //Sets the address to the image getting uploaded and helps in getting the URL of the image.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            uri = data.getData();
            recipeImage.setImageURI(uri);

        }

        else Toast.makeText(this, "You haven't picked any image!",Toast.LENGTH_SHORT).show();
    }

    //Here the image selected from the gallery get saved in the firebase storage.
    public void uploadImage(){


        //created storage reference in firebase i.e. created folder
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("RecipeImage").child(uri.getLastPathSegment());

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Recipe Uploading....");
        progressDialog.show();

        //The URL of the image gets stored as a string in a database
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadRecipe();
                progressDialog.dismiss();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });


    }

    //Calling the Function upload image when button upload recipe is clicked.
    public void btnUploadRecipe(View view) {
        uploadImage();
    }

    //Uploading Recipe like all the other description
    public void uploadRecipe(){

        ProductData productData = new ProductData(
                txt_name.getText().toString(),
                txt_description.getText().toString(),
                txt_price.getText().toString(),
                imageUrl
        );

        //Consider Date wise information
        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());

        //Stores the recipe at firebase storage
        FirebaseDatabase.getInstance().getReference("Recipe")
                .child(myCurrentDateTime).setValue(productData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){

                    Toast.makeText(Upload_Recipe.this, "Recipe Uploaded",Toast.LENGTH_SHORT).show();
                    finish();

            }
        }
    }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_Recipe.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
}}
