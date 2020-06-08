package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.adminapp.studentsPackage.studentAdmin;
import com.example.adminapp.teacherPackage.teacherAdmin;
import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void logoutClick(View view){
        auth.signOut();
        showloginView();
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

    public void showloginView(){
        finish();
        Intent loginIntent = new Intent(this, MainActivity.class);
        startActivity(loginIntent);
    }
}
