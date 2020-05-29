package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void logOutClick(View view){
        auth.signOut();
    }

    public void studentClick(View view){
        showStudentView();
    }

    public void teacherClick(View view){
        showTeacherView();
    }



    public void showStudentView(){
        Intent studentIntent = new Intent(this, studentAdmin.class);
        startActivity(studentIntent);
    }

    public void showTeacherView(){
        Intent teacherIntent = new Intent(this, teacherAdmin.class);
        startActivity(teacherIntent);
    }
}
