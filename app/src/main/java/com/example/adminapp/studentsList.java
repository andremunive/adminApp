package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class studentsList extends AppCompatActivity {

    private String cursoID;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Students");
    private ListView students;
    private ArrayAdapter adapter;
    private ArrayList<String> studentsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        students = findViewById(R.id.studentsListView);
        studentsArray = new ArrayList<>();

        cursoID = getIntent().getStringExtra("cursoID");
        Toast.makeText(this,
                "Curso "+cursoID,
                Toast.LENGTH_SHORT).show();

        listarStudents();
    }


    public void listarStudents(){
        Query query =ref.orderByChild("courseID").equalTo(cursoID);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentsArray.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String nameAux = snapshot.getValue().toString()
                            .split(",")[2].split("=")[1];
                    String lastNameAux = snapshot.getValue().toString()
                            .split(",")[0].split("=")[1];

                    studentsArray.add(nameAux+" "+lastNameAux);
                    adapter = new ArrayAdapter(studentsList.this,
                            android.R.layout.simple_list_item_1,
                            studentsArray);
                    students.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
