package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class signInStudent extends AppCompatActivity {

    private EditText name, lastName, user, email, password;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private Date date = new Date();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy");
    private TextView courseID;
    private Spinner coursesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_student);

        //View
        name = findViewById(R.id.nameTxt);
        lastName = findViewById(R.id.lastNameTxt);
        user = findViewById(R.id.userTxt);
        email = findViewById(R.id.emailTxt);
        password = findViewById(R.id.passwordTxt);
        coursesSpinner = findViewById(R.id.courseSpinner);
        courseID = findViewById(R.id.courseIdTxt);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Modificación del Spinner con la lista de curso ubicada en Strings
        spinnerModifi();
    }

    public void registerClick(View view){
        if(name.getText().toString().isEmpty()){
            name.setError("Campo Obligatorio");
        }else if(lastName.getText().toString().isEmpty()){
            lastName.setError("Campo Obligatorio");
        }else if(user.getText().toString().isEmpty()){
            user.setError("Campo Obligatorio");
        }else if(email.getText().toString().isEmpty()){
            email.setError("Campo Obligatorio");
        }else if(password.getText().toString().isEmpty()){
            password.setError("Campo Obligatorio");
        }else{
            signinMethod(
                    name.getText().toString(),
                    lastName.getText().toString(),
                    user.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString(),
                    courseID.getText().toString()
            );
        }
    }




    public void signinMethod(String nombre, String apellido, String usuario,
                             String correo, String clave, String courseID) {

        students student = new students(
                nombre,
                apellido,
                usuario,
                correo,
                clave,
                courseID
        );

        //Creación del usuario con email y contraseña
        auth.createUserWithEmailAndPassword(email.getText().toString(),
                password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signInStudent.this, "Sirvió", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mDatabase.child("Students").child(usuario).setValue(student)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                        }
                    }
                });
    }

    public void spinnerModifi(){
        final String year = format.format(date).toString();

        final ArrayList<String> courses = new ArrayList<>();
        courses.add("06");
        courses.add("07");
        courses.add("08");
        courses.add("09");
        courses.add("10");
        courses.add("11");

        ArrayAdapter<String> coursesAdapter = new ArrayAdapter<>(
                this,
                R.layout.color_spinner_layout,
                courses
        );

        coursesSpinner.setAdapter(coursesAdapter);

        coursesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseID.setText(courses.get(position)+year);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
