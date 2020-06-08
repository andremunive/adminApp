package com.example.adminapp.teacherPackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.adminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class teachersList extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Teachers");
    private ListView teachers;
    private ArrayAdapter adapter;
    private ArrayList<String> teachersArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_list);

        teachers = findViewById(R.id.teachersListView);
        teachersArray = new ArrayList<>();

        listarTeachers();
    }

    public void listarTeachers(){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    String nameAux = data.getValue().toString()
                            .split(",")[2].split("=")[1];
                    String lastNameAux = data.getValue().toString()
                            .split(",")[0].split("=")[1];

                    teachersArray.add(nameAux+" "+lastNameAux);
                    adapter = new ArrayAdapter(teachersList.this,
                            android.R.layout.simple_list_item_1,
                            teachersArray);
                    teachers.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
