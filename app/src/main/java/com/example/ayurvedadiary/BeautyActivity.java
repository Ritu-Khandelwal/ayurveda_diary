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

public class BeautyActivity extends AppCompatActivity {
    SearchView mySearchView2;
    ListView myList2;

    ArrayList<String> list1;
    ArrayAdapter<String> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);

        //Declaring Searchbar and and list
        mySearchView2 = (SearchView)findViewById(R.id.searchView2);
        myList2 = (ListView)findViewById(R.id.myList2);

        list1 = new ArrayList<String>();

        //Adding elements to the list
        list1.add("Almond products");
        list1.add("Turmeric products");
        list1.add("Fruit products");
        list1.add("Vegetable products");
        list1.add("Alovera products");
        list1.add("Neem products");

        //Use of array adapter which provides access to the data items in a list.
        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list1);
        myList2.setAdapter(adapter1);

        //Functionality for Searchbar
        mySearchView2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter1.getFilter().filter(s);
                return false;
            }
        });

        //Backend for clicking on Almond option and it will send you to the description page.
        myList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(view.getContext(),AlmondActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


}




