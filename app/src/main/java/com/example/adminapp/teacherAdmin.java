package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class teacherAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_admin);
    }

    public void addTeacherClick(View view){
        showAddTeacher();
    }

    public void showAddTeacher(){
        Intent addTeacherIntent = new Intent(this, signInTeacher.class);
        startActivity(addTeacherIntent);
    }
}
