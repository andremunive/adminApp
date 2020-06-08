package com.example.adminapp.teacherPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.adminapp.R;

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
        Intent addIntent = new Intent(this, signInTeacher.class);
        startActivity(addIntent);
    }

    public void listarClick(View view){
        showTeacherList();
    }

    public void showTeacherList(){
        Intent listIntent = new Intent(this, teachersList.class);
        startActivity(listIntent);
    }


}
