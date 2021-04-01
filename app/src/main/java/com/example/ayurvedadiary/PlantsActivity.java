package com.example.ayurvedadiary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.ArrayList;


public class PlantsActivity extends AppCompatActivity {
    SearchView mySearchView;
    ListView myList;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants);

        //Declaring Searchbar and and list
        mySearchView = (SearchView)findViewById(R.id.searchView);
        myList = (ListView)findViewById(R.id.myList);

        list = new ArrayList<String>();

        //Adding elements to the list
        list.add("Amla");
        list.add("Harda");
        list.add("Ginger");
        list.add("Garlic");
        list.add("Coriander");
        list.add("Black pepper");
        list.add("Cinnamon");

        //Use of array adapter which provides access to the data items in a list.
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        myList.setAdapter(adapter);

        //Functionality for Searchbar
        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        //Backend for clicking on Almond option and it will send you to the description page.
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(view.getContext(),AmlaActivity.class);
                    startActivity(intent);
                }
            }
        });
            }


    }
