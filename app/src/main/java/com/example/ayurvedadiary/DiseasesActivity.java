package com.example.ayurvedadiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class DiseasesActivity extends AppCompatActivity {
    SearchView mySearchView1;
    ListView myList1;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases);

        //Declaring Searchbar and and list
        mySearchView1 = (SearchView)findViewById(R.id.searchView1);
        myList1 = (ListView)findViewById(R.id.myList1);

        list = new ArrayList<String>();

        //Adding elements to the list
        list.add("Mouth Ulcer");
        list.add("Stomach Ache");
        list.add("Headache");
        list.add("Acidity");
        list.add("Diabetes");
        list.add("Thyroid");

        //Use of array adapter which provides access to the data items in a list.
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        myList1.setAdapter(adapter);

        //Functionality for searchbar
        mySearchView1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        //Backend for clicking on Ulcer option and it will send you to the description page.
        myList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(view.getContext(),UlcerActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


}



