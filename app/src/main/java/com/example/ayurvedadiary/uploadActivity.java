package com.example.ayurvedadiary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class uploadActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<ProductData> myProductList;
    ProductData mProductData;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    MyAdapter myAdapter;
    EditText txt_Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        //Declaring Variables..Here I have used recycler view to display uploaded items.
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(uploadActivity.this,1 );
        mRecyclerView.setLayoutManager(gridLayoutManager);

        txt_Search = (EditText)findViewById(R.id.txt_searchtext);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Items...");

        myProductList = new ArrayList<>();

        //Adapter for connecting two activities viz, Upload and Productlist..Where all the Uploaded item gets stored.
        myAdapter = new MyAdapter(uploadActivity.this,myProductList);
        mRecyclerView.setAdapter(myAdapter);

        //Items stored in firebase storage like accessing the info stored in firebase.
        //To access a location in the database and read or write data, use getReference().
        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");

        progressDialog.show();

        //For the purpose of retrieval of data from firebase database
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myProductList.clear();

                //Used to determine if datasnapshot has data at a particular location.
                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){

                    ProductData productData = itemSnapshot.getValue(ProductData.class);
                    productData .setKey(itemSnapshot.getKey());
                    myProductList.add(productData);
                }

                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            progressDialog.dismiss();
            }
        });


            //Searchbar functionality
            txt_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }
        });


    }

    //For the purpose of arranging all the products in alphabetical order.
    private void filter(String text) {

        ArrayList<ProductData> filterList = new ArrayList<>();

        for(ProductData item: myProductList){

            if(item.getItemName().toLowerCase().contains(text.toLowerCase())){

                filterList.add(item);
            }
        }

        myAdapter.filteredList(filterList);

    }


    public void btnupload(View view) {
        startActivity(new Intent(this, Upload_Recipe.class));
    }
}
