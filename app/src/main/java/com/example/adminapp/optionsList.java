package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class optionsList extends AppCompatActivity {

    private ListView salones;
    private ArrayList<String> salonesArray;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_list);

        salones = findViewById(R.id.salonesList);
        salonesArray = new ArrayList<String>();
        salonesArray.add("062020");
        salonesArray.add("072020");
        salonesArray.add("082020");
        salonesArray.add("092020");
        salonesArray.add("102020");
        salonesArray.add("112020");

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                salonesArray);
        salones.setAdapter(adapter);

        salones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent listIntent = new Intent(optionsList.this, studentsList.class);
                listIntent.putExtra("cursoID", salonesArray.get(position));
                startActivity(listIntent);
            }
        });

    }
}
