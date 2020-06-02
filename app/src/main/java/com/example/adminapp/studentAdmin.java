package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class studentAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_admin);
    }

    public void addStudentClick(View view){
        showAddStudent();
    }

    public void showAddStudent(){
        Intent addStudentIntent = new Intent(this, signInStudent.class);
        startActivity(addStudentIntent);
    }
}
