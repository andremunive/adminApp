package com.example.adminapp.studentsPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.adminapp.R;
import com.example.adminapp.studentsPackage.signInStudent;

public class studentAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_admin);
    }

    public void addStudentClick(View view){
        showAddStudent();
    }

    public void listarClick(View view){
        showListar();
    }

    public void showListar(){
        Intent optionsIntent = new Intent(this, optionsList.class);
        startActivity(optionsIntent);
    }

    public void showAddStudent(){
        Intent addStudentIntent = new Intent(this, signInStudent.class);
        startActivity(addStudentIntent);
    }
}
